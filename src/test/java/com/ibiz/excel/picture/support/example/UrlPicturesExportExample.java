package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Test;

/**
 * url图片导出excel示例
 *
 * @author MinWeikai
 * @date 2022-01-10 10:08:03
 */
public class UrlPicturesExportExample extends BaseJunitTest {

    @Test
    public void export() {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");

        // 给标题行加上背景色，加颜色时，会对字体加粗
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setFgColorRgb("66cc66");
        UserPicture userPicture;
        for (int r = 0; r < 10; r++) {
            userPicture = new UserPicture();
            userPicture.setAge(15);
            userPicture.setName("测试-" + r);
            userPicture.setPicture(IMG_PATH_1);
            userPicture.setHeaderPicture(getUrl());
            // 根据图片数组和要获取图片的数量，随机从url图片测试集合中取出若干
            userPicture.setUrlPictures(getUrls(5));
            sheet.createRow(userPicture);
            // 对标题行添加上样式
            if (r == 0) {
                sheet.getRow(0).setCellStyle(cellStyle);
            }
        }
        WebUtil.writeExcelTest(workBook, "url图片导出excel示例".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}
