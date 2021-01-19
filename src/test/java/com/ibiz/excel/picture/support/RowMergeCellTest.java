package com.ibiz.excel.picture.support;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 纵向合并行测试
 *
 * @author MinWeikai
 * @date 2021/1/18 17:22
 */
public class RowMergeCellTest {

	static final String CURRENT_PATH = "E:\\test\\";

	public static void main(String[] args) throws IOException {
		Date start = new Date();
		Workbook workBook = Workbook.getInstance(1);
		Sheet sheet = workBook.createSheet("测试");

		// 第一行放标题
		Row rowHead = sheet.createRow(0);
		String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
		List<Cell> nameCells = new ArrayList<>();

		sheet.getMergeCells().add(new MergeCell().setFirstRow(1).setLastRow(3));
		sheet.setAutoMergeCell(true);
		Set<String> colCells = new HashSet<>();
		for (int i = 0; i < excelName.length; i++) {
			Cell cell = new Cell(0, i).setValue(excelName[i]);
			if (i == 0) {
				colCells.add(cell.getCol());
				sheet.setColCells(colCells);
			}
			nameCells.add(cell);
		}
		rowHead.setCells(nameCells);


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
