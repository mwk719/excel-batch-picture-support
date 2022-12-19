package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.pojo.Student;
import com.ibiz.excel.picture.support.processor.ExcelTableProcessor;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MinWeikai
 * @date  2022-12-19 15:15:44
 * @description bug https://gitee.com/mwk719/excel-batch-picture-support/issues/I669N5 测试
 */
public class ExportExample_20221219 extends BaseJunitTest {

    @Test
    public void export() {
        // 模拟需要导出的数据集合
        List<Student> students = new ArrayList<>();
        students.add(new Student("李四", 16, null, null, 0));
        students.add(new Student("张三", 17, null, getPictures(5), 1));
        students.add(new Student("王五", 15, IMG_PATH_1, null, 2));

        // 配置导出excel的表头、顺序、对应导出的数据集合的字段、是否是图片、单元格宽度等
        List<BizExcelRel> excels = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            int orderNo = 2 + i;
            // 给第5列设置为图片
            if(orderNo == 5){
                excels.add(new BizExcelRel("相册", "album", orderNo, true));
            }else {
                excels.add(new BizExcelRel("姓名" + i, "name", orderNo));
            }
        }


        // 创建excel
        Workbook workBook = Workbook.getInstance(100);
        Sheet sheet = workBook.createSheet("测试");

        //要进行合并的列
        sheet.addMergeCell(new MergeCell(0, 0, 0, 2));

        // 创建样式
        CellStyle cellStyle = new CellStyle(0, "F0F0F0");
        // 创建数据字典
        Map<String, String> performanceMap = new HashMap<>();
        performanceMap.put("0", "一般");
        performanceMap.put("1", "良好");
        performanceMap.put("2", "优秀");

        // 构建sheet
        ExcelTableProcessor.sheet(sheet)
                // 添加样式
                .addCellStyle(cellStyle)
                // 添加对应属性字段的数据字典
                .registryEnumMap("performance", performanceMap)
                // 构建excel
                .buildExcel(excels, students);

        WebUtil.writeExcelTest(workBook, "ExportExample_20221219_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }


}
