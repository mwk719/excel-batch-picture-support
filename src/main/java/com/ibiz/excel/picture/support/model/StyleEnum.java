package com.ibiz.excel.picture.support.model;

/**
 * 样式枚举类
 *
 * @author MinWeikai
 * @date 2021/1/19 14:10
 */
public enum StyleEnum {

	/**
	 * 绿色填充色，字体加粗
	 */
	GREEN_B(3),

	/**
	 * 字体-20
	 */
	F20(4);


	/**
	 * 填充色id
	 * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
	 */
	private Integer fillId;

	StyleEnum(Integer fillId) {
		this.fillId = fillId;
	}

	public Integer getFillId() {
		return fillId;
	}
}
