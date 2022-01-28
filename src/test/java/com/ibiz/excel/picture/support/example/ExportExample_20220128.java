package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 导出excel示例
 * 字体设置|合并标题
 *
 * @author MinWeikai
 * @date 2022-01-10 10:08:03
 */
@Ignore
public class ExportExample_20220128 extends BaseJunitTest {

    @Test
    public void export() {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");

        // 标题样式
        CellStyle cellStyle = new CellStyle("F0F0F0");
        Font font = Font.build()
                .setFontName("黑体")
                .setFontHeightInPoints((short) 15)
                .setBoldWeight(true);
        cellStyle.setFont(font);

        // 标题信息
        sheet.createRow(0)
                .addCell(new Cell(0, "测试标题"))
                .setCellStyle(cellStyle)
                .setHeight(30);
        sheet.addMergeCell(new MergeCell(0, 0, 0, 6));

        // 表头样式
        CellStyle cellStyle1 = new CellStyle(1, "66CC66");
        Font font1 = Font.build()
                .setFontHeightInPoints((short) 13)
                .setBoldWeight(true);
        cellStyle1.setFont(font1);
        sheet.addCellStyle(cellStyle1);

        List<UserPicture> list = new ArrayList<>();
        UserPicture userPicture;
        for (int r = 0; r < 5; r++) {
            userPicture = new UserPicture();
            userPicture.setAge(15);
            userPicture.setName("测试-" + r);
            // 导出本地单张图片
            userPicture.setPicture(IMG_PATH_1);
            // 导出url单张图片
            userPicture.setHeaderPicture(getUrl());
            // 导出本地图片集合
            userPicture.setPictures(getPictures(new Random().nextInt(5)));
            // 导出url图片集合
            userPicture.setUrlPictures(getUrls(5));
            list.add(userPicture);
        }
        sheet.startRow(1).write(UserPicture.class).createRow(list);
        WebUtil.writeExcelTest(workBook, "ExportExample_20220127_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}
