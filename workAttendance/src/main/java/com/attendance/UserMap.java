package com.attendance;

import com.google.common.collect.Maps;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class UserMap {

    private static final String filePath = System.getProperty("user.dir") + "/workAttendance//src/main/resources/com/attendance/user.properties";

    public static Map<String, String> getUserMap() {
        Map<String, String> userMap = Maps.newHashMap();
        Properties pps = new Properties();
        try {
            pps.load(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            Enumeration<?> en = pps.propertyNames(); // 得到配置文件的名字
            while (en.hasMoreElements()) {
                String strKey = (String) en.nextElement();
                String strValue = pps.getProperty(strKey);
                userMap.put(strKey, strValue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userMap;
    }

}
