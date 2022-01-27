package com.ibiz.excel.picture.support.model;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class CellStyle{

    /**
     * 默认已有fill样式
     * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
     */
    private int fillId = 0;

    /**
     * 默认已有cellStyles样式
     */
    private int s = 1;

    /**
     * 行号
     */
    private int rowNumber;

    /**
     * fgColor rgb颜色
     * RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     */
    private String fgColorRgb;

    /**
     * 字体
     */
    private Font font;

    public CellStyle() {
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
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
     * @param fgColorRgb RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
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

    public int getFillId() {
        return fillId;
    }

    public void setFillId(int fillId) {
        this.fillId = fillId;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
}
