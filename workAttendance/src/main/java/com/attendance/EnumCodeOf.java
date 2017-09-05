package com.attendance;

import java.lang.reflect.Method;

/**
 * @author: miaoxing
 * DATE:    2017/6/20
 */
public class EnumCodeOf {

    /**
     *
     * @param code
     * @param clazz 需要实现EnumTrait
     * @param <T>
     * @return
     */
    public static <T> T codeOf(Object code, Class<T> clazz) {
        if (code == null) {
            return null;
        }

        T[] types = clazz.getEnumConstants();
        if (types == null || types.length == 0) {
            return null;
        }

        for (T type : types) {
            try {
                Method getCode = clazz.getMethod("getCode");
                if (code.equals(getCode.invoke(type))) {
                    return type;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

}
