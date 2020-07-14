package com.ibiz.excel.picture.support.annotation;

import java.lang.annotation.*;

/**
 * 属性映射转换
 * 暂时RelationShip使用了当前注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Parameter {
    //属性映射到文件中转换后的值
    String value();
}
