package com.ibiz.excel.picture.support.example;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    static final String CURRENT_PATH = "E:\\test\\";

    private final static String IMG_PATH = CURRENT_PATH + "img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";


    public static void main(String[] args) throws IOException {
        Date start = new Date();
        Workbook workBook = Workbook.getInstance(Integer.MAX_VALUE);
        Sheet sheet = workBook.createSheet("测试");

        // 需要在创建行前预设宽度
        //序号宽度
        sheet.addColumnHelper(new ColumnHelper(1, 10));

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
        int num;
        List<Picture> pictures = sheet.getPictures();
        for (int j = 0; j < 5; j++) {
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
                        pictures.add(new Picture(2, row.getRowNumber(), 1000000, IMG_PATH_1));
                        row.setHeight(80);
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
