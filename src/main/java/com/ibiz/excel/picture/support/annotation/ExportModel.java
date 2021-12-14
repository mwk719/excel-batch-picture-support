package com.ibiz.excel.picture.support.annotation;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;

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

    /**
     * 图片所在单元格宽度，图片和单元格宽度一致
     * {@link com.ibiz.excel.picture.support.model.Picture}
     * @return
     */
    int width() default 25;

    /**
     * 有图片的行高设置为100
     * @return {@code WorkbookConstant.PICTURE_ROW_HEIGHT}
     */
    int height() default WorkbookConstant.PICTURE_ROW_HEIGHT;
}
