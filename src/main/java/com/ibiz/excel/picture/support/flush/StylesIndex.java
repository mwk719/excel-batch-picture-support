package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Font;
import com.ibiz.excel.picture.support.model.style.Alignment;
import com.ibiz.excel.picture.support.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 样式下标取值
 *
 * @author MinWeikai
 * @date 2022/1/10 15:31
 */
public class StylesIndex {

    private int fillId = 2;
    private int s = 2;
    private int fontId = 1;

    private final Map<String, CellStyle> styleIndexMap = new HashMap<>();

    protected void addCellStyle(CellStyle cellStyle) {
        if (cellStyle == null) {
            return;
        }

        String styleKey = getStyleKey(cellStyle);
        if (styleIndexMap.containsKey(styleKey)) {
            CellStyle style = styleIndexMap.get(styleKey);
            cellStyle.setS(style.getS());
            cellStyle.setFillId(style.getFillId());
            cellStyle.setExist(true);
            return;
        }

        if (StringUtils.isNotBlank(cellStyle.getFgColorRgb())) {
            cellStyle.setFillId(fillId);
            fillId++;
        }

        cellStyle.setS(s);
        styleIndexMap.put(styleKey, cellStyle);
        s++;

        Font font = cellStyle.getFont();
        if (font != null) {
            font.setFontId(fontId);
            fontId++;
        }
    }

    private String getStyleKey(CellStyle cellStyle) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(cellStyle.getFgColorRgb())) {
            builder.append(cellStyle.getFgColorRgb());
        }

        Font font = cellStyle.getFont();
        if (font != null) {
            builder.append(font);
        }

        Alignment alignment = cellStyle.getAlignment();
        if (alignment != null) {
            builder.append(alignment);
        }
        return builder.toString();
    }

}
