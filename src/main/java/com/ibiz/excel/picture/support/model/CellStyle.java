package com.ibiz.excel.picture.support.model;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class CellStyle {

	/**
	 * 默认已有fill样式
	 * 与对应{@link com.ibiz.excel.picture.support.module.Styles}
	 */
	private int fillId = 33;

	/**
	 * 默认已有cellStyles样式
	 */
	private int s = 4;

	/**
	 * fgColor rgb颜色
	 */
	private String fgColorRgb;

	/**
	 * 样式
	 */
	private StyleEnum styleEnum;

	public CellStyle() {
	}

	/**
	 * 创建单元格前景色
	 * @param fgColorRgb
	 */
	public CellStyle(String fgColorRgb) {
		// fillId+1，生成下一个编号
		fillId = fillId + 1;
		s = s + 1;
		this.fgColorRgb = fgColorRgb;
	}

	public CellStyle(StyleEnum styleEnum) {
		this.styleEnum = styleEnum;
		this.fillId = styleEnum.getFillId();
	}

	public CellStyle(CellStyle cellStyle) {
		// 生成下标
		cellStyle = cellStyle.addIndex();
		this.fillId = cellStyle.fillId;
		this.s = cellStyle.s;
		this.fgColorRgb = cellStyle.fgColorRgb;
	}

	public CellStyle addIndex() {
		// fillId+1，生成下一个编号
		fillId = fillId + 1;
		s = s + 1;
		return this;
	}

	public int getFillId() {
		return fillId;
	}

	public int getS() {
		return s;
	}

	public String getFgColorRgb() {
		return fgColorRgb;
	}

	public void setFgColorRgb(String fgColorRgb) {
		this.fgColorRgb = fgColorRgb;
	}
}
