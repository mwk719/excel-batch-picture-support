package com.ibiz.excel.picture.support.model;

/**
 * Excel导出配置
 *
 * @author MinWeikai
 * @date 2022/2/8 14:45
 */
public class BizExcelRel {

    public BizExcelRel() {
    }

    /**
     * 创建标题，默认使用在列表中的添加顺序
     * @param excelName
     * @param field
     */
    public BizExcelRel(String excelName, String field) {
        this.excelName = excelName;
        this.field = field;
    }

    /**
     * 创建标题，可自定义列的顺序
     * @param excelName
     * @param field
     * @param orderNo
     */
    public BizExcelRel(String excelName, String field, int orderNo) {
        this.excelName = excelName;
        this.field = field;
        this.orderNo = orderNo;
    }

    public BizExcelRel(String excelName, String field, int orderNo, boolean isPicture) {
        this.excelName = excelName;
        this.field = field;
        this.orderNo = orderNo;
        this.isPicture = isPicture;
    }

    public BizExcelRel(String excelName, String field, int orderNo, boolean isPicture, double cellWeight) {
        this.excelName = excelName;
        this.field = field;
        this.orderNo = orderNo;
        this.isPicture = isPicture;
        this.cellWeight = cellWeight;
    }

    /**
     * excel显示的字段名称
     */
    private String excelName;

    /**
     * 字段
     */
    private String field;

    /**
     * 排序
     */
    private int orderNo = 0;

    /**
     * 是否是图片
     */
    private boolean isPicture = false;

    /**
     * 单元格宽度
     */
    private double cellWeight = -1;

    public boolean isPicture() {
        return isPicture;
    }

    public void setPicture(boolean picture) {
        isPicture = picture;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public double getCellWeight() {
        return cellWeight;
    }

    public void setCellWeight(double cellWeight) {
        this.cellWeight = cellWeight;
    }
}
