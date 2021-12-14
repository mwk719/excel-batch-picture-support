package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解图片导出示例
 * todo 建议使用{@link EasyUseExample4} 示例，不过写法有点麻烦，待优化
 *
 * @author MinWeikai
 * @date 2021-12-07 10:59:49
 */
public class AnnotationPictureExportExample {
    static final String CURRENT_PATH = "E:\\test\\";
    private static final String TEMP_PATH = CURRENT_PATH + "excel\\";
    private final static String IMG_PATH = "E:\\test\\img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";


    public static void main(String[] args) throws IOException {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        List<UserPicture> userPictures = new ArrayList<>();
        UserPicture userPicture;
        for (int r = 0; r < 5; r++) {
            userPicture = new UserPicture();
            userPicture.setAge(15);
            userPicture.setName("测试-" + r);
            userPicture.setPicture(IMG_PATH_1);
            userPicture.setHeaderPicture(IMG_PATH_2);
            userPictures.add(userPicture);
        }

        // 创建集合的行数据在excel中
        sheet.createRow(userPictures);
        WebUtil.writeExcelTest(workBook, "注解导出图片示例".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}