package com.ibiz.excel.picture.support.model;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class CellStyle {

    /**
     * 行号
     */
    private int rowNumber;

    /**
     * 默认已有fill样式
     * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
     */
    private int fillId = 33;

    /**
     * 默认已有cellStyles样式
     */
    private int s = 4;

    /**
     * fgColor rgb颜色
     * RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     */
    private String fgColorRgb;

    /**
     * 样式
     */
    private StyleEnum styleEnum;

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

    public CellStyle(StyleEnum styleEnum) {
        this.styleEnum = styleEnum;
        this.fillId = styleEnum.getFillId();
    }

    public CellStyle(CellStyle cellStyle) {
        this.fgColorRgb = cellStyle.fgColorRgb;
    }

    public int getFillId() {
        return fillId;
    }

    public int getS() {
        return s;
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


    public void setFillId(int fillId) {
        this.fillId = fillId;
    }

    public void setS(int s) {
        this.s = s;
    }
}
