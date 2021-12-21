package com.ibiz.excel.picture.support;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 图片放在一个单元格宽度
 *
 * @author MinWeikai
 * @date 2021-01-19 16:03:21
 */
public class PictureCellWidthTest {

	private static final String CURRENT_PATH = "E:\\test\\";

	private final static String IMG_PATH = CURRENT_PATH + "img\\";

	private final static String IMG_PATH_1 = IMG_PATH + "ia_1900002528.jpeg";

	public static void main(String[] args) throws IOException {
		Date start = new Date();
		Workbook workBook = Workbook.getInstance(1);
		Sheet sheet = workBook.createSheet("测试");

		// 需要在创建行前预设宽度
		sheet.setColumnWidth(1, 10)
				.setColumnWidth(3, 50)
				.setColumnWidth(4, 50);

		// 第一行表头
		Row row = sheet.createRow(0);
		String[] excelName = {"序号", "文本2", "图片1", "图片2", "图片3"};
		sheet.getMergeCells().add(new MergeCell(0, 0, 0, excelName.length - 1));
		row.setCells(Collections.singletonList(new Cell(0, 0).setValue("表头")));

		// 第二行放标题
		row = sheet.createRow(1)
				//配置该行颜色，现在默认只有绿色
				.setCellStyle(new CellStyle(StyleEnum.GREEN_B));
		List<Cell> cells = new ArrayList<>();
		for (int i = 0; i < excelName.length; i++) {
			Cell cell = new Cell(1, i).setValue(excelName[i]);
			cells.add(cell);
		}
		row.setCells(cells);

		// 第三行放内容和图片
		row = sheet.createRow(2);

		cells = new ArrayList<>();
		List<Picture> pictures = sheet.getPictures();
		//序号
		cells.add(new Cell(2, 0).setValue("1"));
		for (int i = 0; i < excelName.length; i++) {
			if (i < 2) {
				cells.add(new Cell(2, i).setValue("文本" + i));
			} else {
				//有图片的行,行高设置为100
				row.setHeight(80);
				//每个单元格增加一个图片
				//pictures.add(new Picture(i, row.getRowNumber(),1000000 , IMG_PATH_1));
				//在第三列添加多张图片
				pictures.add(new Picture( row.getRowNumber(), 3,1000000, IMG_PATH_1));
			}
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
