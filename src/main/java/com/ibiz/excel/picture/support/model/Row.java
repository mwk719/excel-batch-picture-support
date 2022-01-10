package com.ibiz.excel.picture.support.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 喻场
 * @date 2020/7/217:33
 */
public class Row {
    private int rowNumber;	//行号
    private int autoHeight = 30; //默认行高
    /**
     * 默认列头为30
     * setHeight后使用设置的值,必须大于0
     */
    private int height;	//列高
    private List<Cell> cells = new ArrayList<>();

    /**
     * 该行样式
     */
    private CellStyle cellStyle;

    public Row(){
        this(1);
    }
    public Row(int rowNumber){
        this.rowNumber = rowNumber;
    }
    public int getHeight() {
        return height <= 0 ? autoHeight : height;
    }

    /**
     * 设置行高
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    public int getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    /**
     * 自动配置行对应的单元格
     *
     * @param cells
     */
    public void autoRowCells(List<Cell> cells) {
        cells.parallelStream().forEach(cell-> cell.autoSetRowNumber(rowNumber));
        this.cells = cells;
    }

    public Cell createCell(int cellNumber){
        Cell cell = new Cell(rowNumber,cellNumber);
        cells.add(cell);
        return cell;
    }

    public void clear(){
        this.cells.clear();
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    /**
     * 设置行样式
     * @param cellStyle
     * @return
     */
    public Row setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
        return this;
    }
}
