package com.ibiz.excel.picture.support.example;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 注解图片集合导出示例
 *
 * @author MinWeikai
 * @date 2021/12/14 10:36
 */
public class AnnotationPicturesExportExample {

    static final String CURRENT_PATH = "E:\\test\\";
    private static final String TEMP_PATH = CURRENT_PATH + "excel\\";
    private final static String IMG_PATH = "E:\\test\\img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";
    private final static String IMG_PATH_3 = IMG_PATH + "3.jpg";

    private final static String IMAGES_PATH = CURRENT_PATH + "images\\";

    public static void main(String[] args) throws IOException {
        Workbook workBook = Workbook.getInstance(56);
        Sheet sheet = workBook.createSheet("测试");
        // 给标题行加上背景色，加颜色时，会对字体加粗
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setFgColorRgb("66cc66");
        // 图片数组
        File[] files = FileUtil.ls(IMAGES_PATH);
        UserPicture userPicture;
        for (int r = 0; r < 15; r++) {
            userPicture = new UserPicture();
            userPicture.setAge(15);
            userPicture.setName("测试-" + r);
            userPicture.setPicture(IMG_PATH_1);
            userPicture.setHeaderPicture(IMG_PATH_2);
            // 根据图片数组和要获取图片的数量，随机从图片数组中取出若干
            userPicture.setPictures(getPictures(files, 5));
            sheet.createRow(userPicture);
            // 对标题行添加上样式
            if(r == 0){
                sheet.getRow(0).setCellStyle(cellStyle);
            }
        }
        WebUtil.writeExcelTest(workBook, "注解导出图片集合示例".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

    /**
     * 根据图片数组和要获取图片的数量，随机从图片数组中取出若干
     * @param files 图片数组
     * @param getCount 获取图片的数量
     * @return
     */
    private static List<String> getPictures(File[] files, int getCount) {
        List<String> list = new ArrayList<>(getCount);
        for (int i = 0; i < getCount; i++) {
            int index = new Random().nextInt(files.length);
            list.add(files[index].getAbsolutePath());
        }
        return list;
    }

}
