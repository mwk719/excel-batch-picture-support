package com.ibiz.excel.picture.support.model;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.style.Alignment;

import java.util.Optional;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class CellStyle {

    public static CellStyle build() {
        return new CellStyle();
    }

    /**
     * 默认已有fill样式
     * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
     */
    private int fillId = 0;

    /**
     * 默认已有cellStyles样式
     */
    private int s = WorkbookConstant.S;

    /**
     * 存在
     */
    private boolean exist = false;


    /**
     * 行号
     */
    private Integer rowNumber;

    /**
     * 是否持续向下该列样式
     */
    private boolean colLast;

    /**
     * 列号
     */
    private Integer colNumber;

    /**
     * fgColor rgb颜色
     * RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     */
    private String fgColorRgb;

    /**
     * 字体
     */
    private Font font;

    /**
     * 边框加粗，默认为1加粗，0不加粗
     */
    private Integer borderBold;

    /**
     * 设置内容对齐方式
     */
    private Alignment alignment;

    public CellStyle() {
    }

    public Font getFont() {
        return font;
    }

    public CellStyle setFont(Font font) {
        this.font = font;
        return this;
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
     *
     * @param rowNumber
     * @param fgColorRgb RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     */
    public CellStyle(Integer rowNumber, String fgColorRgb) {
        this(rowNumber, null, fgColorRgb, null);
    }

    public CellStyle(Integer rowNumber, String fgColorRgb, Boolean borderBold) {
        this(rowNumber, null, fgColorRgb, borderBold);
    }

    public CellStyle(Integer rowNumber, Integer colNumber, String fgColorRgb) {
        this(rowNumber, colNumber, fgColorRgb, null);
    }

    public CellStyle(Integer rowNumber, Integer colNumber, String fgColorRgb, Boolean borderBold) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.fgColorRgb = fgColorRgb;
        this.setBorderBold(borderBold);
    }

    public String getFgColorRgb() {
        return fgColorRgb;
    }

    /**
     * fgColor rgb颜色
     * RGB网页颜色在线取色器 https://link.fobshanghai.com/rgbcolor.htm
     *
     * @return
     */
    public CellStyle setFgColorRgb(String fgColorRgb) {
        this.fgColorRgb = fgColorRgb;
        return this;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public CellStyle setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
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

    public Integer getColNumber() {
        return colNumber;
    }

    public CellStyle setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Integer getBorderBold() {
        return this.borderBold;
    }

    /**
     * 设置边框是否加粗
     *
     * @param borderBold
     * @return
     */
    public CellStyle setBorderBold(Boolean borderBold) {
        this.borderBold = borderBold != null ? (borderBold ? 1 : 0) : null;
        return this;
    }

    public boolean isColLast() {
        return colLast;
    }

    /**
     * 是否持续向下该列样式
     * @param colLast
     * @return
     */
    public CellStyle setColLast(boolean colLast) {
        this.colLast = colLast;
        return this;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Alignment getAlignmentNullDefault() {
        return Optional.ofNullable(getAlignment()).orElse(new Alignment());
    }

    /**
     * 设置内容对齐方式
     * @param alignment
     * @return
     */
    public CellStyle setAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CellStyle{");
        sb.append("fgColorRgb='").append(fgColorRgb).append('\'');
        sb.append(", font=").append(font);
        sb.append(", borderBold=").append(borderBold);
        sb.append(", alignment=").append(alignment);
        sb.append('}');
        return sb.toString();
    }
}
