package com.ibiz.excel.picture.support.example;

import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.BizExcelRel;
import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.pojo.Student;
import com.ibiz.excel.picture.support.processor.ExcelTableProcessor;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态标题excel导出
 *
 * @author MinWeikai
 * @date 2022/2/8 14:49
 */
public class ExportExample_20220208 extends BaseJunitTest {

    @Test
    public void export() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("李四", 16, null, null, 0));
        students.add(new Student("张三", 17, null, getPictures(5), 1));
        students.add(new Student("王五", 15, IMG_PATH_1, null, 2));

        Workbook workBook = Workbook.getInstance(100);
        Sheet sheet = workBook.createSheet("测试");

        List<BizExcelRel> excels = new ArrayList<>();
        excels.add(new BizExcelRel("姓名", "name", 2));
        excels.add(new BizExcelRel("年龄", "age", 3));
        excels.add(new BizExcelRel("表现", "performance", 4));
        excels.add(new BizExcelRel("头像", "headPicture", 5, true, 20));
        excels.add(new BizExcelRel("相册", "album", 6, true));
        // 样式
        CellStyle cellStyle = new CellStyle(0, "F0F0F0");

        Map<String, String> performanceMap = new HashMap<>();
        performanceMap.put("0", "一般");
        performanceMap.put("1", "良好");
        performanceMap.put("2", "优秀");

        // 构建sheet
        ExcelTableProcessor.build(sheet)
                // 添加样式
                .addCellStyle(cellStyle)
                // 添加数据字典
                .registryEnumMap("performance", performanceMap)
                // 构建excel
                .buildExcel(excels, students);

        WebUtil.writeExcelTest(workBook, "ExportExample_20220208_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }


}
