package com.ibiz.excel.picture.support.annotation;

import java.lang.annotation.*;

/**
 * 有当前注解的文件自动完全写入磁盘
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoWrite {
    String dir() default "";
    int sort() default Integer.MAX_VALUE;
}
