package com.ibiz.excel.picture.support;

import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.stream.IntStream;

@SpringBootApplication
public class ExcelXApplication {

    public static void main(String[] args) {
        Workbook workBook = Workbook.getInstance(200);
        Sheet sheet = workBook.createSheet("导出个表");
        /*User u1 = new User("z1", 12, "a1", "D:\\1.png");
        User u2 = new User("z2", 12, "a2", "");
        User u3 = new User("z3", 12, "a3", "D:\\1.png");
        User u4 = new User("z3", 12, "a3", "");*/
        IntStream.range(0, 1).forEach(r -> {
            /*sheet.createRow(u1);
            sheet.createRow(u2);
            sheet.createRow(u3);
            sheet.createRow(u4);*/
        });
        workBook.close();
    }
}
