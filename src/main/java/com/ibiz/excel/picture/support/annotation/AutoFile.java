package com.ibiz.excel.picture.support.annotation;

import java.lang.annotation.*;

/**
 * module目录下 使用当前注解自动创建文件
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoFile {
    /**文件名称*/
    String fileName() default "";
    /**子目录*/
    String subDir() default "";
    /**别名*/
    String alias();
    /**文件尾*/
    String xmlEnd() default "";
    boolean writeXmlHead() default true;
}
