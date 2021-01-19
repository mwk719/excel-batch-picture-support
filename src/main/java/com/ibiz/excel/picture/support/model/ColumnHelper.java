package com.ibiz.excel.picture.support.model;

/**
 * 单元格辅助功能类
 *
 * @author MinWeikai
 * @date 2021/1/19 15:41
 */
public class ColumnHelper {

	private Integer columnIndex;

	private Integer width;

	public ColumnHelper() {
	}

	public ColumnHelper(Integer columnIndex, Integer width) {
		this.columnIndex = columnIndex;
		this.width = width;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public Integer getWidth() {
		return width;
	}
}
