package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.pojo.Student;
import com.ibiz.excel.picture.support.pojo.UserPicture;
import com.ibiz.excel.picture.support.processor.ExcelTableProcessor;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

/**
 * @author MinWeikai
 * @date  2023-01-10 17:07:51
 * @description 单个单元格添加样式示例
 */
@Ignore
public class ExportExample_20230110 extends BaseJunitTest {

    @Test
    public void export() {
        Workbook workBook = Workbook.getInstance();
        Sheet sheet = workBook.createSheet("测试");
        // 给标题行加上背景色，加颜色时，会对字体加粗
        sheet.addCellStyle(new CellStyle(0, "66cc66"));
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
//            sheet.createRow(userPicture);
            sheet.createRow(userPicture).getCell(0).setCellStyle(new CellStyle("9F79EE"));
        }
        WebUtil.writeExcelTest(workBook, "ExportExample_20230110_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

    @Test
    public void export1() {
        // 模拟需要导出的数据集合
        List<Student> students = new ArrayList<>();
        students.add(new Student("李四", 16, null, null, 0));
        students.add(new Student("张三", 17, null, getPictures(5), 1));
        students.add(new Student("王五", 15, IMG_PATH_1, null, 2));

        // 配置导出excel的表头、顺序、对应导出的数据集合的字段、是否是图片、单元格宽度等
        List<BizExcelRel> excels = new ArrayList<>();
        excels.add(new BizExcelRel("姓名", "name", 2));
        excels.add(new BizExcelRel("年龄", "age", 3));
        excels.add(new BizExcelRel("表现", "performance", 4));
        excels.add(new BizExcelRel("头像", "headPicture", 5, true, 20));
        excels.add(new BizExcelRel("相册", "album", 6, true));

        // 设置对全局操作边框是否加粗
        WorkbookConstant.setBorderBold(false);
        // 创建excel
        Workbook workBook = Workbook.getInstance(100);
        Sheet sheet = workBook.createSheet("测试");

        //要进行合并的列
        sheet.addMergeCell(new MergeCell(0, 0, 0, 2));

        // 创建样式
        List<CellStyle> cellStyles = Arrays.asList(
                new CellStyle(0, "66cc66", true),
                new CellStyle(0, 3, "9F79EE", true),
                new CellStyle(1, 2, "9F79EE"),
                new CellStyle(2, 0, "9F79EE"),
                new CellStyle(2, 1, "9F79EE"),
                new CellStyle(3, 2, "66cc66")
        );

        // 创建数据字典
        Map<String, String> performanceMap = new HashMap<>();
        performanceMap.put("0", "一般");
        performanceMap.put("1", "良好");
        performanceMap.put("2", "优秀");

        // 构建sheet
        ExcelTableProcessor.sheet(sheet)
                // 添加样式
                .addCellStyle(cellStyles)
                // 添加对应属性字段的数据字典
                .registryEnumMap("performance", performanceMap)
                // 构建excel
                .buildExcel(excels, students);

        WebUtil.writeExcelTest(workBook, "ExportExample_20230110_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }


}
