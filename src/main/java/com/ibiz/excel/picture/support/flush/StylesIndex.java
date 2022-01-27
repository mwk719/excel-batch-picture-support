package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Font;
import com.ibiz.excel.picture.support.util.StringUtils;

/**
 * 样式下标取值
 *
 * @author MinWeikai
 * @date 2022/1/10 15:31
 */
public class StylesIndex {

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
     * 字体id
     */
    private int fontId = 0;

    /**
     * 是否设置字体
     */
    private boolean isSetFont;

    /**
     * 设置下标样式
     *
     * @param cellStyle
     */
    protected void addCellStyle(CellStyle cellStyle) {
        // 字体
        Font font = cellStyle.getFont();
        if(font != null){
            this.isSetFont = true;
        }else {
            this.isSetFont = false;
        }
        this.addIndex(cellStyle);
        if(StringUtils.isNotBlank(cellStyle.getFgColorRgb())){
            cellStyle.setFillId(fillId);
        }
        cellStyle.setS(s);
        if(font != null){
            font.setFontId(fontId);
        }
    }

    protected void addIndex(CellStyle cellStyle) {
        // fillId+1，生成下一个编号
        if(StringUtils.isNotBlank(cellStyle.getFgColorRgb())){
            fillId = (fillId == 0 ? 1 : fillId) + 1;
        }
        s = s + 1;
        if(isSetFont){
            fontId = fontId + 1;
        }
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

    public int getFontId() {
        return fontId;
    }

    public void setFontId(int fontId) {
        this.fontId = fontId;
    }

    public void setSetFont(boolean setFont) {
        isSetFont = setFont;
    }
}
