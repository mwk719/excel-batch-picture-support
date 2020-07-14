package com.ibiz.excel.picture.support.model;

/**
 * @auther 喻场
 * @date 2020/7/217:31
 */
public class Cell {
    private final String[] CELL_NUMBER_LINE = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private int cellNumber;//第几列
    private int rowNumber;//行号
    private String colNumber = "";//列号 例 A1  B1
    private String col = ""; //单元格列 例 A B AA AB
    private int colDataNumber;  //列对应值序号
    private String value;	//单元格值

    public String getColNumber() {
        return colNumber;
    }

    public int getColDataNumber() {
        return colDataNumber;
    }

    public void setColDataNumber(int colDataNumber) {
        this.colDataNumber = colDataNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public Cell(int rowNumber,int cellNumber) {
        super();
        this.rowNumber = rowNumber;
        this.cellNumber = cellNumber;
        int line = cellNumber;
        if(cellNumber >= CELL_NUMBER_LINE.length){
            line = cellNumber - CELL_NUMBER_LINE.length;
            colNumber += "A";
        }
        col += colNumber + CELL_NUMBER_LINE[line];
        colNumber += CELL_NUMBER_LINE[line] + (rowNumber + 1);
    }

    public String getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Cell(int cellNumber) {
        this(0,cellNumber);
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }
}
