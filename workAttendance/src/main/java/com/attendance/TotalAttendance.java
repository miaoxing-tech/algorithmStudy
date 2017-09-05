package com.attendance;

/**
 * @author: miaoxing
 * DATE:    2017/6/21
 */
public class TotalAttendance {

    /**
     * 姓名
     */
    private String name;

    /**
     * 登记号码
     */
    private String userNo;

    /**
     * 出勤天数
     */
    private int attendanceDays;

    /**
     * 正常出勤天数
     */
    private int normalAttendanceDays;

    /**
     * 考勤记录不全天数
     */
    private int notEnoughAttendanceDays;

    /**
     * 上午迟到天数
     */
    private int lateDays;

    /**
     * 下午早退天数
     */
    private int leaveEarlyDays;

    /**
     * 全天不足9小时天数
     */
    private int lessThan9Days;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public int getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(int attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public int getNormalAttendanceDays() {
        return normalAttendanceDays;
    }

    public void setNormalAttendanceDays(int normalAttendanceDays) {
        this.normalAttendanceDays = normalAttendanceDays;
    }

    public int getNotEnoughAttendanceDays() {
        return notEnoughAttendanceDays;
    }

    public void setNotEnoughAttendanceDays(int notEnoughAttendanceDays) {
        this.notEnoughAttendanceDays = notEnoughAttendanceDays;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public int getLeaveEarlyDays() {
        return leaveEarlyDays;
    }

    public void setLeaveEarlyDays(int leaveEarlyDays) {
        this.leaveEarlyDays = leaveEarlyDays;
    }

    public int getLessThan9Days() {
        return lessThan9Days;
    }

    public void setLessThan9Days(int lessThan9Days) {
        this.lessThan9Days = lessThan9Days;
    }
}
