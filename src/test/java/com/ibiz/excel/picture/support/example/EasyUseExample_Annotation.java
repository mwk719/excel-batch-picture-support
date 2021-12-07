package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;

import java.io.IOException;
import java.util.UUID;

/**
 * 注解使用导出
 * todo 使用注解目前一个单元格只能放一张图片，存在bug图片所在下边框不是加粗实线，待解决
 * todo 建议使用{@link EasyUseExample4} 示例，不过写法有点麻烦，待优化
 * @author MinWeikai
 * @date 2021-12-07 10:59:49
 */
public class EasyUseExample_Annotation {
    static final String CURRENT_PATH = "E:\\test\\";
    private static final String TEMP_PATH = CURRENT_PATH + "excel\\";
    private final static String IMG_PATH = "E:\\test\\img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";


    public static void main(String[] args) throws IOException {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        UserPicture u1;
        for (int r = 0; r < 2; r++) {
            u1 = new UserPicture();
            u1.setAge(15);
            u1.setName("测试-" + r);
            u1.setPicture(IMG_PATH_1);
            sheet.createRow(u1);
        }
        WebUtil.writeExcelTest(workBook, "使用注解导出".concat(String.valueOf(UUID.randomUUID())).concat(".xlsx"), TEMP_PATH);
    }

}
