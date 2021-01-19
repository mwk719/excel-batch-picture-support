package com.ibiz.excel.picture.support;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 行填充色
 *
 * @author MinWeikai
 * @date 2021-01-19 13:58:14
 */
public class RowFgColorTest {

	static final String CURRENT_PATH = "E:\\test\\";

	public static void main(String[] args) throws IOException {
		Date start = new Date();
		Workbook workBook = Workbook.getInstance(1);
		Sheet sheet = workBook.createSheet("测试");

		// 第一行表头
		Row row = sheet.createRow(0);
		String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
		sheet.getMergeCells().add(new MergeCell(0, 0, 0, excelName.length - 1));
		row.setCells(Collections.singletonList(new Cell(0, 0).setValue("表头")));

		// 第二行放标题
		row = sheet.createRow(1)
				//配置该行颜色，现在默认只有绿色
				.setRowStyle(new RowStyle(FgColorEnum.GREEN));
		List<Cell> cells = new ArrayList<>();
		for (int i = 0; i < excelName.length; i++) {
			Cell cell = new Cell(1, i).setValue(excelName[i]);
			cells.add(cell);
		}
		row.setCells(cells);

		// 第三行放内容
		row = sheet.createRow(2);
		cells = new ArrayList<>();
		for (int i = 0; i < excelName.length; i++) {
			Cell cell = new Cell(2, i).setValue("文本" + i);
			cells.add(cell);
		}
		row.setCells(cells);


		File file = createFile();
		BufferedOutputStream os = FileUtil.getOutputStream(file);
		workBook.write(os);
		workBook.close();
		Date end = new Date();
		System.out.println("file capital :" + (file.length() / 1024 / 1024) + "M  name :" + file.getName());
		System.out.println("file cost time :" + (end.getTime() - start.getTime()));
		os.close();
	}


	private static File createFile() {
		String dir = CURRENT_PATH + "excel/";
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdir();
		}
		String name = CURRENT_PATH + "excel/" + UUID.randomUUID() + ".xlsx";
		return new File(name);
	}
}
