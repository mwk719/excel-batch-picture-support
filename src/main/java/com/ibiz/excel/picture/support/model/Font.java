package com.ibiz.excel.picture.support.model;

/**
 * 字体
 *
 * @author MinWeikai
 * @date 2022/1/27 11:47
 */
public class Font{

    /**
     * 字体id
     */
    private int fontId = 0;

    /**
     * 设置字体的名称
     */
    private String fontName = "宋体";

    /**
     * 是否加粗，默认不加粗
     */
    private boolean boldWeight = false;

    /**
     * 字体颜色
     */
    private String color;

    /**
     * 字体大小
     */
    private short fontHeightInPoints = 11;

    public static Font build() {
        return new Font();
    }

    public String getFontName() {
        return fontName;
    }

    public Font setFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public boolean isBoldWeight() {
        return boldWeight;
    }

    public Font setBoldWeight(boolean boldWeight) {
        this.boldWeight = boldWeight;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Font setColor(String color) {
        this.color = color;
        return this;
    }

    public short getFontHeightInPoints() {
        return fontHeightInPoints;
    }

    public Font setFontHeightInPoints(short fontHeightInPoints) {
        this.fontHeightInPoints = fontHeightInPoints;
        return this;
    }

    public int getFontId() {
        return fontId;
    }

    public void setFontId(int fontId) {
        this.fontId = fontId;
    }



}
