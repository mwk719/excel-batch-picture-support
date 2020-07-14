package com.ibiz.excel.picture.support.annotation;

import java.lang.annotation.*;

/**
 * module目录下使用该注解会自动注入sheet实例
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface InjectSheet {
}
