package com.ibiz.excel.picture.support;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 多图片导入测试
 *
 * @author MinWeikai
 * @date 2021-01-15 09:46:32
 */
public class PicturesDemo {
	static final String CURRENT_PATH = "E:\\test\\";

	private final static String IMG_PATH = "E:\\test\\img\\";

	private static Integer SUM = 0;
	private static Integer N = 0;
	private static Integer _COUNT = 0;

	public static void main(String[] args) throws IOException {
		Date start = new Date();
		Workbook workBook = Workbook.getInstance(1);
		Sheet sheet = workBook.createSheet("测试");
		File[] files = FileUtil.ls(IMG_PATH);
		SUM = files.length;
		UserPicture u1;
		for (int r = 0; r < 10; r++) {
			if (N == 0) {
				_COUNT = 0;
			}
			u1 = new UserPicture();
			u1.setAge(15);
			u1.setName("测试-" + r);
			u1.setPicture(files[getIndex(N, 0)].getAbsolutePath());
			u1.setPicture1(files[getIndex(N, 1)].getAbsolutePath());
			u1.setPicture2(files[getIndex(N, 2)].getAbsolutePath());
			u1.setPicture3(files[getIndex(N, 3)].getAbsolutePath());
			u1.setPicture4(files[getIndex(N, 4)].getAbsolutePath());
			u1.setPicture5(files[getIndex(N, 5)].getAbsolutePath());
			u1.setPicture6(files[getIndex(N, 6)].getAbsolutePath());
			u1.setPicture7(files[getIndex(N, 7)].getAbsolutePath());
			u1.setPicture8(files[getIndex(N, 8)].getAbsolutePath());
			sheet.createRow(u1);

			_COUNT++;
			N = _COUNT * 9;
		}

		File file = createFile();
		OutputStream os = new FileOutputStream(file);
		workBook.write(os);
		workBook.close();
		Date end = new Date();
		System.out.println("file capital :" + (file.length() / 1024 / 1024) + "M  name :" + file.getName());
		System.out.println("file cost time :" + (end.getTime() - start.getTime()));
		os.close();
	}

	private static int getIndex(int i, int i1) {
		int count = i + i1;
		if (count >= SUM) {
			N = 0;
			count = 0;
			_COUNT = 0;
		}
		return count;
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
