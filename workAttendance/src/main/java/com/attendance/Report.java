package com.attendance;

import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author: miaoxing
 * DATE:    2017/6/20
 */
public class Report {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DAY_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int EIGHT_HOURS = 8 * 60 * 60 * 1000;
    private static final int NINE_HOURS = 9 * 60 * 60 * 1000;

    public Workbook reportExcel(String filePath) throws Exception {
        // 建立excel文件
        Workbook wb = new XSSFWorkbook();

        // 工作表名称
        XSSFSheet sheet1 = (XSSFSheet) wb.createSheet("考情结果");
        XSSFSheet sheet2 = (XSSFSheet) wb.createSheet("考情统计");

        // 工作表1
        XSSFRow rowHead = sheet1.createRow(0);  //生成行头
        String[] title = Title.EVERYDAY_ATTENDANCE;
        for (int z = 0; z < title.length; z++) {
            rowHead.createCell(z).setCellValue(title[z]);
        }
        int rowNum = 1;

        Map<String, Map<String, List<Input>>> read = ReadIntoMemory.read(filePath);
        Map<String, String> userMap = UserMap.getUserMap();
        Map<String, TotalAttendance> attendanceMap = Maps.newHashMap();

        for (String userNo : read.keySet()) {
            Map<String, List<Input>> dates = read.get(userNo);

            for (String date : dates.keySet()) {
                List<Input> inputs = dates.get(date);
                if (inputs == null || inputs.size() == 0) {
                    continue;
                }

                XSSFRow rowContent = sheet1.createRow(rowNum);
                rowNum++;
                int j = 0;
                Date startTime = null, startNoonTime = null, endNoonTime = null, endTime = null;
                for (Input input : inputs) {
                    if (input.getStatus() == AttendanceStatus.START_WORK) {
                        startTime = input.getDate();
                        continue;
                    }

                    if (input.getStatus() == AttendanceStatus.END_WORK) {
                        endTime = input.getDate();
                        continue;
                    }

                    if (input.getStatus() == AttendanceStatus.START_NOON_BREAK) {
                        startNoonTime = input.getDate();
                        continue;
                    }

                    if (input.getStatus() == AttendanceStatus.END_NOON_BREAK) {
                        endNoonTime = input.getDate();
                        continue;
                    }
                }

                TotalAttendance totalAttendance;
                if (attendanceMap.containsKey(userNo)) {
                    totalAttendance = attendanceMap.get(userNo);
                } else {
                    totalAttendance = new TotalAttendance();
                }

                rowContent.createCell(j++).setCellValue(userMap.get(userNo)); // 姓名
                totalAttendance.setName(userMap.get(userNo));
                totalAttendance.setUserNo(userNo);
                totalAttendance.setAttendanceDays(totalAttendance.getAttendanceDays() + 1);

                rowContent.createCell(j++).setCellValue(userNo); // 登记号码

                String startTimeString = startTime == null ? "" : DAY_FORMATTER.format(startTime);
                String endTimeString = endTime == null ? "" : DAY_FORMATTER.format(endTime);
                String startNoonTimeString = startNoonTime == null ? "" : DAY_FORMATTER.format(startNoonTime);
                String endNoonTimeString = endNoonTime == null ? "" : DAY_FORMATTER.format(endNoonTime);
                rowContent.createCell(j++).setCellValue(startTimeString); // 开始时间
                rowContent.createCell(j++).setCellValue(startNoonTimeString); // 午休开始时间
                rowContent.createCell(j++).setCellValue(endNoonTimeString); // 午休结束时间
                rowContent.createCell(j++).setCellValue(endTimeString); // 结束时间

                if (startTime == null || endTime == null) {
                    rowContent.createCell(j++).setCellValue(""); // 总时长
                    rowContent.createCell(j++).setCellValue(date); // 考勤日期
                    rowContent.createCell(j++).setCellValue(""); // 日期情况
                    rowContent.createCell(j++).setCellValue(""); // 开始签到情况
                    rowContent.createCell(j++).setCellValue(""); // 结束签到情况
                    rowContent.createCell(j++).setCellValue(""); // 一天工作时长

                    rowContent.createCell(j++).setCellValue("✘"); // 是否满9小时
                    rowContent.createCell(j++).setCellValue("异常"); // 一天考勤情况

                } else {
                    long timeLong = endTime.getTime() - startTime.getTime();
                    Date period = new Date();
                    period.setTime(timeLong);
                    FORMATTER.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                    String periodString = FORMATTER.format(period);
                    boolean timeBoolean = timeLong >= NINE_HOURS;
                    boolean isRight = timeBoolean;

                    if (startNoonTime == null || endNoonTime == null) {
                        isRight = false;
                    }

                    rowContent.createCell(j++).setCellValue(periodString); // 总时长
                    rowContent.createCell(j++).setCellValue(date); // 考勤日期
                    rowContent.createCell(j++).setCellValue(""); // 日期情况

                    Instant startInstant = startTime.toInstant();
                    ZoneId zone = ZoneId.systemDefault();
                    LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(startInstant, zone);
                    LocalDateTime nineTwenty = startLocalDateTime.withHour(9).withMinute(20).withSecond(0).withNano(0);
                    LocalDateTime nine = startLocalDateTime.withHour(9).withMinute(0).withSecond(0).withNano(0);
                    if (startLocalDateTime.isAfter(nine) && startLocalDateTime.isBefore(nineTwenty)) {
                        rowContent.createCell(j++).setCellValue("【早上迟到"); // 开始签到情况
                        totalAttendance.setLateDays(totalAttendance.getLateDays() + 1);
                        isRight = false;
                    } else if (startLocalDateTime.isAfter(nineTwenty)) {
                        rowContent.createCell(j++).setCellValue("【旷工"); // 开始签到情况
                        totalAttendance.setLateDays(totalAttendance.getLateDays() + 1);
                        isRight = false;
                    } else {
                        rowContent.createCell(j++).setCellValue("【√"); // 开始签到情况
                    }

                    rowContent.createCell(j++).setCellValue(timeBoolean ? "√】" : "下午早退】"); // 结束签到情况
                    rowContent.createCell(j++).setCellValue(periodString); // 一天工作时长

                    rowContent.createCell(j++).setCellValue(timeBoolean ? "√" : "✘"); // 是否满9小时
                    rowContent.createCell(j++).setCellValue(isRight ? "正常" : "异常"); // 一天考勤情况

                    if (isRight) {
                        totalAttendance.setNormalAttendanceDays(totalAttendance.getNormalAttendanceDays() + 1);
                    }

                    if (!timeBoolean) {
                        totalAttendance.setLeaveEarlyDays(totalAttendance.getLeaveEarlyDays() + 1);
                        totalAttendance.setLessThan9Days(totalAttendance.getLessThan9Days() + 1);
                    }
                }

                if (startTime == null || endTime == null || startNoonTime == null || endNoonTime == null) {
                    totalAttendance.setNotEnoughAttendanceDays(totalAttendance.getNotEnoughAttendanceDays() + 1);
                }

                attendanceMap.put(userNo, totalAttendance);
            }

        }


        // 工作表2
        XSSFRow rowHead2 = sheet2.createRow(0);  //生成行头
        String[] title2 = Title.TOTAL_ATTENDANCE;
        for (int z = 0; z < title2.length; z++) {
            rowHead2.createCell(z).setCellValue(title2[z]);
        }
        int rowNum2 = 1;

        for (String totalAttendanceKey : attendanceMap.keySet()) {
            TotalAttendance totalAttendance = attendanceMap.get(totalAttendanceKey);

            XSSFRow rowContent2 = sheet2.createRow(rowNum2);
            rowNum2++;
            int j = 0;

            rowContent2.createCell(j++).setCellValue(totalAttendance.getName()); // 姓名
            rowContent2.createCell(j++).setCellValue(totalAttendance.getUserNo()); // 登记号码
            rowContent2.createCell(j++).setCellValue(totalAttendance.getAttendanceDays()); // 出勤天数
            rowContent2.createCell(j++).setCellValue(totalAttendance.getNormalAttendanceDays()); // 正常出勤天数
            rowContent2.createCell(j++).setCellValue(totalAttendance.getNotEnoughAttendanceDays()); // 考勤记录不全天数
            rowContent2.createCell(j++).setCellValue(totalAttendance.getLateDays()); // 上午迟到天数
            rowContent2.createCell(j++).setCellValue(totalAttendance.getLeaveEarlyDays()); // 下午早退天数
            rowContent2.createCell(j++).setCellValue(totalAttendance.getLessThan9Days()); // 全天不足9小时天数
        }

        return wb;
    }

    public static void main(String[] args) throws Exception {
        Report report = new Report();
        Workbook workbook = report.reportExcel("/Users/miaoxing/Downloads/201705.txt");

        File file = new File("/Users/miaoxing/Downloads/考情结果.xlsx");
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

}
