package com.ibiz.excel.picture.support;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 自定义标题，填入文本和图片
 *
 * @author MinWeikai
 * @date 2021-01-15 15:19:44
 */
public class CustomTitleDemo {
	static final String CURRENT_PATH = "E:\\test\\";

	private final static String IMG_PATH = CURRENT_PATH + "img\\";

	private final static String IMG_PATH_1 = IMG_PATH + "ia_1900002528.jpeg";
	private final static String IMG_PATH_2 = IMG_PATH + "ia_1200000645.jpg";


	public static void main(String[] args) throws IOException {
		Date start = new Date();
		Workbook workBook = Workbook.getInstance(1);
		Sheet sheet = workBook.createSheet("测试");

		// 第一行放标题
		Row row = sheet.createRow(0);
		String[] excelName = {"文本1", "文本2", "图片1", "图片2", "图片3"};
		List<Cell> nameCells = new ArrayList<>();
		for (int i = 0; i < excelName.length; i++) {
			nameCells.add(new Cell(0, i).setValue(excelName[i]));
		}
		row.setCells(nameCells);

		// 第二行放文本图片
		row = sheet.createRow(1);
		List<Cell> valueCells = new ArrayList<>();
		List<Picture> pictures = sheet.getPictures();
		for (int i = 0; i < excelName.length; i++) {
			if (i < 2) {
				valueCells.add(new Cell(1, i).setValue("文本"));
			} else {
				//有图片的行,行高设置为100
				row.setHeight(WorkbookConstant.PICTURE_ROW_HEIGHT);
				//增加图片
				pictures.add(new Picture(row.getRowNumber(), i, IMG_PATH_1));
			}
		}
		row.setCells(valueCells);


		File file = createFile();
		OutputStream os = new FileOutputStream(file);
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
