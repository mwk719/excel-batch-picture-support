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

    public BizExcelRel(String excelName, String field, boolean isEnum, String lookupCode, int orderNo) {
        this.excelName = excelName;
        this.field = field;
        this.isEnum = isEnum;
        this.lookupCode = lookupCode;
        this.orderNo = orderNo;
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
     * 是否是枚举
     */
    private boolean isEnum = false;

    /**
     * 数据字典code
     */
    private String lookupCode;

    /**
     * 排序
     */
    private int orderNo = 0;

    /**
     * 是否是图片
     */
    private boolean isPicture = false;

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

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean anEnum) {
        isEnum = anEnum;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
