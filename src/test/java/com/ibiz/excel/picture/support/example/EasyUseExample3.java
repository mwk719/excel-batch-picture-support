package com.ibiz.excel.picture.support.example;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 简化设置EasyUseExample3
 * 1. 需要文本列表信息
 * 2. 需要单元格填充多张图片
 * 3. 需要表头
 *
 * @author MinWeikai
 * @date 2021-02-07 16:47:17
 */
public class EasyUseExample3 {
    static final String CURRENT_PATH = "E:\\test\\";

    private final static String IMG_PATH = CURRENT_PATH + "img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";


    public static void main(String[] args) throws IOException {
        Date start = new Date();
        Workbook workBook = Workbook.getInstance(1);
        Sheet sheet = workBook.createSheet("测试");

        // 需要在创建行前预设宽度
        sheet.setColumnWidth(1, 10)
                .setColumnWidth(3, 50)
                .setColumnWidth(4, 50)
                .setColumnWidth(5, 50);

        // 第一行表头
        Row row = sheet.createRow(0)
                //字体20
                .setRowStyle(new RowStyle(StyleEnum.F20));
        row.autoRowCells(Collections.singletonList(new Cell(0).setValue("表头")));

        // 第二行放标题
        String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
        //要进行合并的列
        sheet.getMergeCells().add(new MergeCell(0, 0, 0, excelName.length - 1));
        row = sheet.createRow(1)
                //配置该行颜色，现在默认只有绿色
                .setRowStyle(new RowStyle(StyleEnum.GREEN_B));
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < excelName.length; i++) {
            cells.add(new Cell(i).setValue(excelName[i]));
        }
        row.autoRowCells(cells);

        // 第三行放内容
        row = sheet.createRow(2);
        List<Picture> pictures = sheet.getPictures();
        cells = new ArrayList<>();
        for (int i = 0; i < excelName.length; i++) {
            if (i < 2) {
                cells.add(new Cell(i).setValue("文本"));
            } else {
                //有图片的行,行高设置为100
                row.setHeight(80);
                //每个单元格增加一个图片
                //pictures.add(new Picture(i, row.getRowNumber(),1000000 , IMG_PATH_1));

                //在第二列添加多张图片
                pictures.add(new Picture(2, row.getRowNumber(), 1000000, IMG_PATH_1));
                //在第三列添加多张图片
                pictures.add(new Picture(3, row.getRowNumber(), 1000000, IMG_PATH_2));
                pictures.add(new Picture(4, row.getRowNumber(), 1000000, IMG_PATH_1));
            }
        }
        row.autoRowCells(cells);

        File file = createFile();
        BufferedOutputStream os = FileUtil.getOutputStream(file);
        workBook.write(os);
        workBook.close();
        Date end = new Date();
        System.out.println("file capital :" + (file.length() / 1024 / 1024) + "M  name :" + file.getName());
        System.out.println("file cost time :" + (end.getTime() - start.getTime()));
        os.close();
    }


    private static File createFile() {
        String dir = CURRENT_PATH + "excel/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }
        String name = CURRENT_PATH + "excel/" + UUID.randomUUID() + ".xlsx";
        return new File(name);
    }

}
