package com.ibiz.excel.picture.support.util;

import com.ibiz.excel.picture.support.annotation.Parameter;
import com.ibiz.excel.picture.support.module.RelationShip;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @auther 喻场
 * @date 2020/7/619:47
 */
public class CovertUtil {

    public static String covert(RelationShip ship) {
        StringBuilder content = new StringBuilder();
        content.append("<Relationship ");
        Field[] declaredFields = ship.getClass().getDeclaredFields();
        Arrays.stream(declaredFields).filter(f -> null != f.getAnnotation(Parameter.class)).forEach(f -> {
            f.setAccessible(true);
            Parameter parameter = f.getAnnotation(Parameter.class);
            //元素key
            String tagKey = parameter.value();
            try {
                content.append(tagKey).append("=\"").append((String)f.get(ship)).append("\" ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        content.append(" />");
        return content.toString();
    }
}
