package com.ibiz.excel.picture.support.model.style;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.abt.XmlAbstract;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MinWeikai
 * @date 2023/7/7 18:09
 * @description excel 内容对齐
 */
@XmlRootElement(name = "alignment")
public class Alignment extends XmlAbstract {

    /**
     * 对齐方向默认值
     */
    public static final String CENTER = "center";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String TOP = "top";
    public static final String BOTTOM = "bottom";

    public static Alignment build() {
        return new Alignment();
    }

    /**
     * 自动换行
     */
    private Integer wrapText = WorkbookConstant.WRAP_TEXT;

    /**
     * 垂直对齐方式
     */
    private String vertical = WorkbookConstant.VERTICAL;

    /**
     * 水平对齐方式
     */
    private String horizontal = WorkbookConstant.HORIZONTAL;

    @XmlAttribute
    public Integer getWrapText() {
        return wrapText;
    }

    /**
     * 1自动换行，0不换行
     *
     * @param wrapText
     * @return
     */
    public Alignment setWrapText(Integer wrapText) {
        this.wrapText = wrapText;
        return this;
    }

    @XmlAttribute
    public String getVertical() {
        return vertical;
    }

    public Alignment setVertical(String vertical) {
        this.vertical = vertical;
        return this;
    }

    @XmlAttribute
    public String getHorizontal() {
        return horizontal;
    }

    public Alignment setHorizontal(String horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Alignment{");
        sb.append("wrapText=").append(wrapText);
        sb.append(", vertical='").append(vertical).append('\'');
        sb.append(", horizontal='").append(horizontal).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
