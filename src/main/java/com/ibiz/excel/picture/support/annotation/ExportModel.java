package com.ibiz.excel.picture.support.annotation;

import com.ibiz.excel.picture.support.constants.PictureSourceContent;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.Picture;

import java.lang.annotation.*;

/**
 * 导出模型
 * 使用该注解程序会根据相应属性对单元格做设置
 *
 * @author MinWeikai
 * @date 2021-12-22 14:43:43
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExportModel {
    /**
     * 排序，必填，且不能重复
     *
     */
    int sort() ;

    /**
     * 是否是图片
     */
    boolean isPicture() default false;

    /**
     * 表头
     */
    String title() default "";

    /**
     * 已这列为准进行合并列
     */
    boolean mergeMaster() default false;

    /**
     * 这一列是否要合并
     */
    boolean merge() default false;

    /**
     * 图片所在单元格宽度，图片和单元格宽度一致，建议使用默认 1500000作为宽度
     * {@link com.ibiz.excel.picture.support.model.Picture}
     *
     * @return
     */
    int width() default WorkbookConstant.PICTURE_WEIGHT;

    /**
     * 图片所在单元格行高度，单元格会自适应图片的高度
     * 如需要自定义图片高度，注意：在导出类中有多个图片字段，需要在每个图片字段上注解配置自定义的高度
     *
     * @return
     */
    int height() default WorkbookConstant.PICTURE_HEIGHT;

    /**
     * 图片来源 默认为0,自动判断图片来源
     * {@link Picture#autoPictureSourceByPath()}
     * 不设置的话自动匹配,也可自己指定
     * {@link PictureSourceContent}
     *
     * @return
     */
    int pictureSource() default 0;
}
