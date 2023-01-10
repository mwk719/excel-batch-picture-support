package com.ibiz.excel.picture.support.model;

/**
 * @auther 喻场
 * @date 2020/7/217:31
 */
public class Cell {
    private final String[] CELL_NUMBER_LINE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int cellNumber;//第几列
    private int rowNumber;//行号
    private String colNumber = "";//列号 例 A1  B1
    private String col = ""; //单元格列 例 A B AA AB
    private int colDataNumber;  //列对应值序号
    private String value;    //单元格值

    /**
     * 该单元格样式
     */
    private CellStyle cellStyle;

    public Cell(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public Cell(int rowNumber, int cellNumber) {
        super();
        this.rowNumber = rowNumber;
        this.cellNumber = cellNumber;
        this.autoSetCell(rowNumber, cellNumber);
    }

    public Cell(int cellNumber, String value) {
        this.cellNumber = cellNumber;
        this.value = value;
    }

    /**
     * 自动设置Cell顺序
     *
     * @param rowNumber
     * @param cellNumber
     */
    public void autoSetCell(int rowNumber, int cellNumber) {
        int line = cellNumber;
        if (cellNumber >= CELL_NUMBER_LINE.length) {
            line = cellNumber - CELL_NUMBER_LINE.length;
            colNumber += "A";
        }
        col += colNumber + CELL_NUMBER_LINE[line];
        colNumber = "";
        colNumber += CELL_NUMBER_LINE[line] + (rowNumber + 1);
    }

    public String getColNumber() {
        return colNumber;
    }

    public int getColDataNumber() {
        return colDataNumber;
    }

    public void setColDataNumber(int colDataNumber) {
        this.colDataNumber = colDataNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * 设置RowNumber并自动排序
     *
     * @param rowNumber
     */
    public void autoSetRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
        this.autoSetCell(rowNumber, this.cellNumber);
    }

    public String getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }

    public Cell setValue(String value) {
        this.value = value;
        return this;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    /**
     * 设置该单元格样式
     *
     * @param cellStyle
     * @return
     */
    public Cell setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
        return this;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }
}
