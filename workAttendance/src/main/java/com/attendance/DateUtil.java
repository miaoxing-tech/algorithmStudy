package com.attendance;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: miaoxing
 * DATE:    17/06/20
 */
public class DateUtil {

    /**
     * 将 yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd 格式的字符串转成Date
     *
     * @param dateStr
     * @return
     */
    public static Date parseToSpecifiedDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd HH:mm:ss");
            return format.parse(dateStr);
        } catch (ParseException ignored) {
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * String按格式转成Date
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseToDate(String strDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
