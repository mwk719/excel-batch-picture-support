package com.ibiz.excel.picture.support.model;

/**
 * 填充色枚举类
 *
 * @author MinWeikai
 * @date 2021/1/19 14:10
 */
public enum FgColorEnum {

	/**
	 * 绿色填充色
	 */
	GREEN(3);

	/**
	 * 填充色id
	 * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
	 */
	private Integer fillId;

	FgColorEnum(Integer fillId) {
		this.fillId = fillId;
	}

	public Integer getFillId() {
		return fillId;
	}
}
