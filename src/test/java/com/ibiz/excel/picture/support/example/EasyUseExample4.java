package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.util.WebUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 简化设置EasyUseExample4
 * 1. 需要文本列表信息
 * 2. 需要单元格填充多张图片
 * 3. 需要表头
 * 4. 多行数据写入
 *
 * @author MinWeikai
 * @date 2021-02-07 16:47:17
 */
public class EasyUseExample4 {
    private static final String CURRENT_PATH = "E:\\test\\";

    private static final String TEMP_PATH = CURRENT_PATH + "excel\\";

    private final static String IMG_PATH = CURRENT_PATH + "img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";


    public static void main(String[] args) {
        Workbook workBook = Workbook.getInstance(100);
        Sheet sheet = workBook.createSheet("测试");

        // 需要在创建行前预设宽度
        // 序号宽度 有时需要单独设置序号宽度窄一点
        sheet.setColumnWidth(1, 5)
                // 设置第三列宽度
                .setColumnWidth(3, 50);

        // 第一行表头
        Row row = sheet.createRow(0).setCellStyle(new CellStyle("cc3300"));
        row.addCell(new Cell(0).setValue("表头"));

        // 第二行放标题
        String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
        //要进行合并的列
        sheet.addMergeCell(new MergeCell(0, 0, 0, excelName.length - 1));

        row = sheet.createRow(1).setCellStyle(new CellStyle("996699"));
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < excelName.length; i++) {
            cells.add(new Cell(i).setValue(excelName[i]));
        }
        row.autoRowCells(cells);

        // 第三行放内容
        int num;
        List<Picture> pictures = sheet.getPictures();
        for (int j = 0; j < 1; j++) {
            // 第三行放内容
            num = j + 2;
            row = sheet.createRow(num);

            cells = new ArrayList<>();
            int index = 0;
            for (int i = 0; i < excelName.length; i++) {
                Object propertyValue = null;
                switch (i) {
                    case 0:
                        propertyValue = num - 1;
                        break;
                    case 1:
                        propertyValue = "文本2";
                        break;
                    case 2:
                        //在第二列添加多张图片
                        pictures.add(new Picture(row.getRowNumber(), 2, WorkbookConstant.PICTURE_WEIGHT, IMG_PATH_1));
                        pictures.add(new Picture(row.getRowNumber(), 2, WorkbookConstant.PICTURE_WEIGHT, IMG_PATH_2));
                        row.setHeight(90);
                        break;
                    case 3:
                        propertyValue = "333";
                        break;
                    case 4:
                        propertyValue = "144411";
                        break;
                    default:
                }
                cells.add(new Cell(num, index++).setValue(propertyValue == null ? "" : propertyValue.toString()));
                row.setCells(cells);
            }
        }

        WebUtil.writeExcelTest(workBook, "多数据图测试导出".concat(String.valueOf(UUID.randomUUID())).concat(".xlsx"), TEMP_PATH);
    }


}
