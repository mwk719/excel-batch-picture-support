package com.ibiz.excel.picture.support.example;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Test;

import java.io.File;
import java.util.Random;

/**
 * 注解图片集合导出示例
 *
 * @author MinWeikai
 * @date 2021/12/14 10:36
 */
public class AnnotationPicturesExportExample extends BaseJunitTest {

    @Test
    public void export() {
        Workbook workBook = Workbook.getInstance(56);
        Sheet sheet = workBook.createSheet("测试");

//         需要在创建行前预设宽度
//        sheet.setColumnWidth(6, 100);

        // 给标题行加上背景色，加颜色时，会对字体加粗
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setFgColorRgb("66cc66");
        // 图片数组
        File[] files = FileUtil.ls(IMAGES_PATH);
        UserPicture userPicture;
        for (int r = 0; r < 10; r++) {
            userPicture = new UserPicture();
            userPicture.setAge(15);
            userPicture.setName("测试-" + r);
            userPicture.setPicture(IMG_PATH_1);
            userPicture.setHeaderPicture(getUrl());
            // 根据图片数组和要获取图片的数量，随机从图片数组中取出若干
            userPicture.setPictures(getPictures(files, new Random().nextInt(5)));
            sheet.createRow(userPicture);
                    // 不设置时，自适应图片的高度
//                    .setHeight(200);
            // 对标题行添加上样式
            if(r == 0){
                sheet.getRow(0).setCellStyle(cellStyle);
            }
        }
        WebUtil.writeExcelTest(workBook, "注解导出图片集合示例".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}
