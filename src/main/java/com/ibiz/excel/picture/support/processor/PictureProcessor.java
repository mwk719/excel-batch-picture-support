package com.ibiz.excel.picture.support.processor;

import com.ibiz.excel.picture.support.annotation.ExportModel;
import com.ibiz.excel.picture.support.model.Picture;
import com.ibiz.excel.picture.support.model.Row;
import com.ibiz.excel.picture.support.model.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * excel中图片处理器
 *
 * @author MinWeikai
 * @date 2022/2/11 13:55
 */
public class PictureProcessor {

    public PictureProcessor(Sheet sheet) {
        this.sheet = sheet;
    }

    public static PictureProcessor build(Sheet sheet) {
        return new PictureProcessor(sheet);
    }

    private final Sheet sheet;

    /**
     * 添加图片
     *
     * @param row
     * @param cellNumber
     * @param value
     * @param model
     */
    public void addPictures(Row row, int cellNumber, Object value, ExportModel model) {
        // 图片所在单元格宽度，图片和单元格宽度一致
        int width = model.width();
        // 图片所在单元格行高度
        int height = model.height();
        addPictures(row, cellNumber, value, model.pictureSource(), width, height);
    }

    /**
     * 添加图片
     *
     * @param row
     * @param cellNumber
     * @param value
     * @param pictureSource
     * @param width
     * @param height
     */
    public void addPictures(Row row, int cellNumber, Object value, int pictureSource, int width, int height) {
        List<String> values = getValues(value);
        // 计算行高
        calculateRowHeight(row, height);
        // 计算单元格宽度根据单元格中值的数量
        calculateColumnWidth(cellNumber, width, values.size());
        //增加图片
        values.forEach(val ->
                sheet.getPictures().add(
                        new Picture(row.getRowNumber(), cellNumber, width, height, val, pictureSource)
                                // 自动设置图片来源
                                .autoPictureSourceByPath()
                ));
    }

    /**
     * 获取value集合
     *
     * @param value
     * @return
     */
    private List<String> getValues(Object value) {
        List<String> values = new ArrayList<>();
        if (value instanceof List) {
            values = (List<String>) value;
        } else {
            values.add(String.valueOf(value));
        }
        return values;
    }

    /**
     * 计算行高
     *
     * @param row
     * @param height
     */
    private void calculateRowHeight(Row row, int height) {
        // 设置行高自适应于图片的高度
        row.setHeight((height / 12600) - 1);
    }

    /**
     * 计算单元格宽度根据单元格中值的数量
     *
     * @param cellNumber
     * @param width
     * @param valuesSize 图片数
     */
    private void calculateColumnWidth(int cellNumber, int width, int valuesSize) {
        // 实际宽
        int actualWidth = width / 76923;
        //
        // 数组为空时，给默认值1，否则单元格宽度会被计算成0
        valuesSize = valuesSize == 0 ? 1 : valuesSize;
        // 设置单元格宽度，单元格宽度 = 图片宽*图片数量
        double columnWidth = actualWidth * valuesSize + valuesSize / 1.5;
        sheet.setColumnWidth(cellNumber + 1, columnWidth);
    }
}
