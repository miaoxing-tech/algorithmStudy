package com.attendance;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReadIntoMemory {

    private static final Splitter LINE_BREAK_SPLITTER = Splitter.on("\t").trimResults();
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public static Map<String, Map<String, List<Input>>> read(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp;
            // Map<UserNo, Map<DATE(yyyy-MM-dd), List<Input>>>
            Map<String, Map<String, List<Input>>> map = Maps.newHashMap();

            // 1.分行读取文件
            for (temp = br.readLine(); temp != null; temp = br.readLine()) {
                String data = temp.trim();
                if (StringUtils.isBlank(data)) {
                    continue;
                }

                // 2.处理每行数据
                List<String> inputs = LINE_BREAK_SPLITTER.splitToList(data);
                Input input = new Input();
                input.setUserNo(inputs.get(0));

                Date date = DateUtil.parseToSpecifiedDate(inputs.get(1));
                String dateString = FORMATTER.format(date);

                input.setDate(date);
                input.setStatus(AttendanceStatus.codeOf(Integer.valueOf(inputs.get(3))));

                if (map.containsKey(input.getUserNo())) {
                    Map<String, List<Input>> resultMap = map.get(input.getUserNo());
                    if (resultMap.containsKey(dateString)) {
                        List<Input> result = resultMap.get(dateString);
                        Input deleteInput = null;
                        Input addInput = null;
                        if (input.getStatus() == AttendanceStatus.START_NOON_BREAK || input.getStatus() == AttendanceStatus.END_NOON_BREAK) {
                            addInput = input;
                        }
                        boolean needAddWork = true;
                        for (Input inputSingle : result) {
                            if (input.getStatus() == inputSingle.getStatus()) {
                                needAddWork = false;
                                if (input.getStatus() == AttendanceStatus.START_WORK && input.getDate().getTime() < inputSingle.getDate().getTime()) {
                                    deleteInput = inputSingle;
                                    needAddWork = true;
                                    break;
                                }

                                if (input.getStatus() == AttendanceStatus.END_WORK && input.getDate().getTime() > inputSingle.getDate().getTime()) {
                                    deleteInput = inputSingle;
                                    needAddWork = true;
                                    break;
                                }

                                if (input.getStatus() == AttendanceStatus.START_NOON_BREAK || input.getStatus() == AttendanceStatus.END_NOON_BREAK) {
                                    addInput = null;
                                    break;
                                }
                            }
                        }

                        if (needAddWork) {
                            result.add(input);
                        }
                        if (deleteInput != null) {
                            result.remove(deleteInput);
                        }
                        if (addInput != null) {
                            result.add(addInput);
                        }

                        resultMap.put(dateString, result);

                    } else {
                        List<Input> result = Lists.newArrayList(input);
                        resultMap.put(dateString, result);
                    }
                    map.put(input.getUserNo(), resultMap);

                } else {
                    Map<String, List<Input>> resultMap = Maps.newHashMap();
                    resultMap.put(dateString, Lists.newArrayList(input));
                    map.put(input.getUserNo(), resultMap);
                }
            }

            return map;

        } else {
            throw new FileNotFoundException("文件不存在, path = " + filePath);
        }
    }

}
