package com.ibiz.excel.picture.support.model;

/**
 * 读取内置字体
 *
 * @author MinWeikai
 * @date 2021/1/20 11:57
 */
public enum  FontEnum {

	/**
	 * font size 20
	 */
	F20(22),

	F10(3)
	;
	/**
	 * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
	 */
	private Integer fillId;

	FontEnum(Integer fillId) {
		this.fillId = fillId;
	}

	public Integer getFillId() {
		return fillId;
	}
}
