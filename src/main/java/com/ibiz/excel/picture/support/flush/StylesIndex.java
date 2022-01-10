package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.CellStyle;

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
    private int fillId = 33;

    /**
     * 默认已有cellStyles样式
     */
    private int s = 4;

    /**
     * 设置下标样式
     *
     * @param cellStyle
     */
    protected void addCellStyle(CellStyle cellStyle) {
        this.addIndex();
        cellStyle.setFillId(fillId);
        cellStyle.setS(s);
    }

    protected void addIndex() {
        // fillId+1，生成下一个编号
        fillId = fillId + 1;
        s = s + 1;
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
