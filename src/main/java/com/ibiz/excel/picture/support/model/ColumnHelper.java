package com.ibiz.excel.picture.support.model;

/**
 * 单元格辅助功能类
 *
 * @author MinWeikai
 * @date 2021/1/19 15:41
 */
public class ColumnHelper {

    /**
     * 单元格列号， 1表示A列
     */
    private int columnIndex;

    /**
     * 单元格宽度, 默认10
     */
    private int width = 10;

    /**
     * 设置第几列单元格宽度
     *
     * @param columnIndex 第几列单元格
     * @param width       宽度
     */
    public ColumnHelper(int columnIndex, int width) {
        this.columnIndex = columnIndex;
        this.width = width;
    }

    /**
     * 设置第几列单元格宽度
     * @param columnIndex 第几列单元格，默认宽度{@code width}
     */
    public ColumnHelper(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getWidth() {
        return width;
    }
}
