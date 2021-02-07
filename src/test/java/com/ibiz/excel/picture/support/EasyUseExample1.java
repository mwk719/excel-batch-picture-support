package com.ibiz.excel.picture.support;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 简化设置EasyUseExample1
 * 1. 需要文本列表信息
 * 2. 需要单元格填充单个图片
 *
 * @author MinWeikai
 * @date 2021-02-07 16:47:17
 */
public class EasyUseExample1 {
    static final String CURRENT_PATH = "E:\\test\\";

    private final static String IMG_PATH = CURRENT_PATH + "img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";


    public static void main(String[] args) throws IOException {
        Date start = new Date();
        Workbook workBook = Workbook.getInstance(1);
        Sheet sheet = workBook.createSheet("测试");

        // 第一行放标题
        String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
        //要进行合并的列
        sheet.getMergeCells().add(new MergeCell(0, 0, 0, excelName.length - 1));
        Row row = sheet.createRow(1)
                //配置该行颜色，现在默认只有绿色
                .setRowStyle(new RowStyle(StyleEnum.GREEN_B));
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < excelName.length; i++) {
            cells.add(new Cell(i).setValue(excelName[i]));
        }
        row.autoRowCells(cells);

        // 第二行放内容
        row = sheet.createRow(2);
        List<Picture> pictures = sheet.getPictures();
        cells = new ArrayList<>();
        for (int i = 0; i < excelName.length; i++) {
            if (i < 2) {
                cells.add(new Cell(i).setValue("文本"));
            } else {
                //有图片的行,行高设置为100
                row.setHeight(WorkbookConstant.PICTURE_ROW_HEIGHT);
                //增加图片
                pictures.add(new Picture(row.getRowNumber(), i, IMG_PATH_1));
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
