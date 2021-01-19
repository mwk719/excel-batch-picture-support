package com.ibiz.excel.picture.support.model;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class RowStyle {

	private int fillId;

	private FgColorEnum fgColor;

	public RowStyle() {
	}

	public RowStyle(FgColorEnum fgColor) {
		this.fgColor = fgColor;
		fillId = this.fgColor.getFillId();
	}

	public int getFillId() {
		return fillId;
	}

	public FgColorEnum getFgColor() {
		return fgColor;
	}
}
