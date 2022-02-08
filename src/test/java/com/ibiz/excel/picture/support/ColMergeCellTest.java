package com.ibiz.excel.picture.support;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 横向合并列测试
 *
 * @author MinWeikai
 * @date 2021/1/18 17:22
 */
public class ColMergeCellTest {

	static final String CURRENT_PATH = "E:\\test\\";

	public static void main(String[] args) throws IOException {
		Date start = new Date();
		Workbook workBook = Workbook.getInstance(1);
		Sheet sheet = workBook.createSheet("测试");

		// 第一行表头
		Row row = sheet.createRow(0);
		String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
		row.addCell(new Cell(0, 0).setValue("表头"));
		sheet.addMergeCell(new MergeCell(0, 0, 0, excelName.length - 1));

		// 第二行放标题
		row = sheet.createRow(1);
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
