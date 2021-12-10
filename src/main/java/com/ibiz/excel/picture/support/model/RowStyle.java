package com.ibiz.excel.picture.support.model;

/**
 * 控制该行样式
 *
 * @author MinWeikai
 * @date 2021/1/19 14:04
 */
public class RowStyle {

	private int fillId;

	/**
	 * fgColor rgb颜色
	 */
	private String fgColorRgb;

	/**
	 * 样式
	 */
	private StyleEnum styleEnum;

    public RowStyle(String fgColorRgb) {
        this.fgColorRgb = fgColorRgb;
    }

	public RowStyle(StyleEnum styleEnum) {
		this.styleEnum = styleEnum;
		this.fillId = styleEnum.getFillId();
	}

	public int getFillId() {
		return fillId;
	}

    public String getFgColorRgb() {
        return fgColorRgb;
    }

    public void setFgColorRgb(String fgColorRgb) {
        this.fgColorRgb = fgColorRgb;
    }
}
