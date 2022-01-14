package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.CellStyle;
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
 *
 * @author MinWeikai
 * @date 2022-01-10 10:08:03
 */
@Ignore
public class ExportExample_20220110 extends BaseJunitTest {

    @Test
    public void export() {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        // 给标题行加上背景色，加颜色时，会对字体加粗
        sheet.addCellStyle(new CellStyle(0, "66cc66"));
        UserPicture userPicture;
        for (int r = 0; r < 101; r++) {
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
            sheet.createRow(userPicture);
        }
        WebUtil.writeExcelTest(workBook, "ExportExample_20220110_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

    @Test
    public void export1() {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        // 给标题行加上背景色，加颜色时，会对字体加粗
        sheet.addCellStyle(new CellStyle(0, "66cc66"));
        List<UserPicture> list = new ArrayList<>();
        UserPicture userPicture;
        for (int r = 0; r < 101; r++) {
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
        WebUtil.writeExcelTest(workBook, "ExportExample_20220110_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}
