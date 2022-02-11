package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.pojo.Student;
import com.ibiz.excel.picture.support.processor.ExcelTableProcessor;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MinWeikai
 * @date 2022/2/8 14:49
 */
public class ExportExample_20220208 extends BaseJunitTest {

    @Test
    public void export() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("李四", 16));
        students.add(new Student("张三", 17, getPictures(5)));
        students.add(new Student("王五", 15, IMG_PATH_1));

        Workbook workBook = Workbook.getInstance(100);
        Sheet sheet = workBook.createSheet("测试");

        List<BizExcelRel> excels = new ArrayList<>();
        excels.add(new BizExcelRel("姓名", "name", 1));
        excels.add(new BizExcelRel("年龄", "age", 2));
        excels.add(new BizExcelRel("头像", "headPicture", 3, true, 20));
        excels.add(new BizExcelRel("相册", "album", 4, true));
        // 样式
        CellStyle cellStyle = new CellStyle(0,"F0F0F0");

        // 构建sheet
        ExcelTableProcessor.build(sheet)
                // 添加样式
                .addCellStyle(cellStyle)
                // 构建excel
                .buildExcel(excels, students);

        WebUtil.writeExcelTest(workBook, "ExportExample_20220208_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }


}