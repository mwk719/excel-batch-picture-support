package com.ibiz.excel.picture.support.util;

/**
 * @auther yc
 * @date 2020/7/149:16
 */
public class StringUtils {

    public static boolean isBlank(String val) {
        return null == val ? true : "".equals(val.trim());
    }

    public static boolean isNotBlank(String val) {
        return !isBlank(val);
    }

    public static boolean equals(String v1, String v2) {
        return null == v1 && null == v2 ? true
                : null != v1 && v1.equals(v2) ? true
                : null != v2 && v2.equals(v1);
    }

}
