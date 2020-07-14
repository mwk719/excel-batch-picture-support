package com.ibiz.excel.picture.support.model;

/**
 * @auther 喻场
 * @date 2020/7/217:36
 */
public class MergeCell {
    private int startRowNumber; //合并单元格起始行
    private int endRowNumber; //合并单元格结束行

    public MergeCell startRowNumber(int startRowNumber) {
        this.startRowNumber = startRowNumber;
        return this;
    }

    public MergeCell endRowNumber(int endRowNumber) {
        this.endRowNumber = endRowNumber;
        return this;
    }

    public int getStartRowNumber() {
        return startRowNumber;
    }

    public int getEndRowNumber() {
        return endRowNumber;
    }

}
