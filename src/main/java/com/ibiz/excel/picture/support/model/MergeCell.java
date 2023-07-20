package com.ibiz.excel.picture.support.model;

/**
 * 合并单元格
 *
 * @author MinWeikai
 * @date 2021-01-19 09:18:15
 */
public class MergeCell {

    private int firstRow;
    private int firstCol;
    private int lastRow;
    private int lastCol;

    public MergeCell() {
    }

    /**
     * 构造此合并单元格对象可以进行合并
     * 下标，从0开始
     *
     * @param firstRow
     * @param firstCol
     * @param lastRow
     * @param lastCol
     */
    public MergeCell(int firstRow, int firstCol, int lastRow, int lastCol) {
        this.firstRow = firstRow;
        this.firstCol = firstCol;
        this.lastRow = lastRow;
        this.lastCol = lastCol;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public MergeCell setFirstRow(int firstRow) {
        this.firstRow = firstRow;
        return this;
    }

    public int getFirstCol() {
        return firstCol + 1;
    }

    public MergeCell setFirstCol(int firstCol) {
        this.firstCol = firstCol;
        return this;
    }

    public int getLastRow() {
        return lastRow;
    }

    public MergeCell setLastRow(int lastRow) {
        this.lastRow = lastRow;
        return this;
    }

    public int getLastCol() {
        return lastCol + 1;
    }

    public MergeCell setLastCol(int lastCol) {
        this.lastCol = lastCol;
        return this;
    }

}
