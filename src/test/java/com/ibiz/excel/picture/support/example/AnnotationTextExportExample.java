package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;

import java.io.IOException;

/**
 * 注解无图导出示例
 *
 * @author MinWeikai
 * @date 2021-12-08 11:18:49
 */
public class AnnotationTextExportExample {
    static final String CURRENT_PATH = "E:\\test\\";
    private static final String TEMP_PATH = CURRENT_PATH + "excel\\";
    private final static String IMG_PATH = "E:\\test\\img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";


    public static void main(String[] args) throws IOException {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        UserPicture u1;
        for (int r = 0; r < 5; r++) {
            u1 = new UserPicture();
            u1.setAge(15);
            u1.setName("测试-" + r);
            u1.setDepartment("研发部");
            sheet.createRow(u1);
        }
        WebUtil.writeExcelTest(workBook, "注解导出文本示例".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

}
