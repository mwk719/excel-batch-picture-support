package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Font;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 导出excel示例
 * 字体设置
 * @author MinWeikai
 * @date 2022-01-10 10:08:03
 */
@Ignore
public class ExportExample_20220127 extends BaseJunitTest {

    @Test
    public void export() {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        // 给第一行加上背景色和字体配置
        CellStyle cellStyle = new CellStyle(0, "F0F0F0");
        Font font = Font.build()
                .setFontName("黑体")
                .setFontHeightInPoints((short) 15)
                .setColor("FF4040")
                .setBoldWeight(true);
        cellStyle.setFont(font);
        sheet.addCellStyle(cellStyle);

        // 给第三行字体放大到18
        CellStyle cellStyle1 = new CellStyle();
        Font font1 = new Font();
        font1.setFontHeightInPoints((short) 18);
        cellStyle1.setRowNumber(2);
        cellStyle1.setFont(font1);
        sheet.addCellStyle(cellStyle1);

        // 给第五行加上背景色
        sheet.addCellStyle(new CellStyle(4, "AB82FF"));

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
        sheet.write(UserPicture.class).createRow(list);
        WebUtil.writeExcelTest(workBook, "ExportExample_20220127_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}
