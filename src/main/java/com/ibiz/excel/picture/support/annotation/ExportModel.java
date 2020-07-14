package com.ibiz.excel.picture.support.annotation;

import java.lang.annotation.*;

/**
 * 导出模型
 * 使用该注解程序会根据相应属性对单元格做设置
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExportModel {
    /**排序*/
    int sort() default 0;
    /**是否是图片*/
    boolean isPicture() default false;
    /**表头*/
    String title() default "";
    /**已这列为准进行合并列*/
    boolean mergeMaster() default false;
    /**这一列是否要合并*/
    boolean merge() default false;
}
