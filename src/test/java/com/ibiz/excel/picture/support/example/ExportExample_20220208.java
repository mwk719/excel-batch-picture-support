package com.ibiz.excel.picture.support.example;

import cn.hutool.core.bean.BeanUtil;
import com.ibiz.excel.picture.support.common.BaseJunitTest;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.pojo.Student;
import com.ibiz.excel.picture.support.util.WebUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MinWeikai
 * @date 2022/2/8 14:49
 */
public class ExportExample_20220208 extends BaseJunitTest {

    @Test
    public void export() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("李四", 16));
        students.add(new Student("张三", 17));

        Workbook workBook = Workbook.getInstance(100);
        Sheet sheet = workBook.createSheet("测试");

        List<BizExcelRel> excels = new ArrayList<>();
        excels.add(new BizExcelRel("姓名", "name", 1));
        excels.add(new BizExcelRel("年龄", "age", 2));


        buildExcel(sheet, excels, students);

        WebUtil.writeExcelTest(workBook, "ExportExample_20220208_".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

    /**
     * 构建excel
     *
     * @param sheet  excel
     * @param excels excel属性配置
     * @param list   需要导出的集合对象
     */
    private static void buildExcel(Sheet sheet, List<BizExcelRel> excels, List<? extends BizExcelPojoInterface> list) {
        // 开始行放标题
        int startRow = 0;
        Row row = sheet.createRow(startRow);
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < excels.size(); i++) {
            cells.add(new Cell(i).setValue(excels.get(i).getExcelName()));
        }
        row.autoRowCells(cells);
        int num;
        List<Picture> pictures = sheet.getPictures();
        for (int j = 0; j < list.size(); j++) {
            // 开始行的下一行放内容
            num = j + 1;
            row = sheet.createRow(num);
            cells = new ArrayList<>();
            int index = 0;
            BizExcelPojoInterface excelPojoInterface = list.get(j);
            excels = excels.stream().sorted(Comparator.comparing(BizExcelRel::getOrderNo)).collect(Collectors.toList());
            for (BizExcelRel excel : excels) {
                Object propertyValue = BeanUtil.getFieldValue(excelPojoInterface, excel.getField());
                cells.add(new Cell(num, index++).setValue(propertyValue == null ? "" : propertyValue.toString()));
                row.setCells(cells);
            }
        }
    }
}
