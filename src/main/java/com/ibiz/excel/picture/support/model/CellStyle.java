package com.ibiz.excel.picture.support.model;

import com.ibiz.excel.picture.support.flush.StylesIndex;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class CellStyle extends StylesIndex {

    /**
     * 行号
     */
    private int rowNumber;

    /**
     * fgColor rgb颜色
     * RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     */
    private String fgColorRgb;

    public CellStyle() {
    }

    /**
     * 创建单元格前景色
     *
     * @param fgColorRgb
     */
    public CellStyle(String fgColorRgb) {
        this.fgColorRgb = fgColorRgb;
    }

    /**
     * 根据行号设置颜色
     * @param rowNumber
     * @param fgColorRgb
     */
    public CellStyle(int rowNumber, String fgColorRgb) {
        this.rowNumber = rowNumber;
        this.fgColorRgb = fgColorRgb;
    }

    public CellStyle(CellStyle cellStyle) {
        this.fgColorRgb = cellStyle.fgColorRgb;
    }

    public String getFgColorRgb() {
        return fgColorRgb;
    }

    /**
     * fgColor rgb颜色
     * RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     */
    public void setFgColorRgb(String fgColorRgb) {
        this.fgColorRgb = fgColorRgb;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

}
