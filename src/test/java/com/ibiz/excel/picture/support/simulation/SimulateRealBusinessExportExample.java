package com.ibiz.excel.picture.support.simulation;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.UserPicture;
import com.ibiz.excel.picture.support.model.CellStyle;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.model.Workbook;
import com.ibiz.excel.picture.support.util.WebUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 模拟真实业务场景导出
 *
 * @author MinWeikai
 * @date 2021-12-22 15:36:46
 */
public class SimulateRealBusinessExportExample {

    static final String CURRENT_PATH = "E:\\test\\";
    private static final String TEMP_PATH = CURRENT_PATH + "excel\\";
    private final static String IMG_PATH = "E:\\test\\img\\";

    private final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    private final static String IMG_PATH_2 = IMG_PATH + "2.jpg";
    private final static String IMG_PATH_3 = IMG_PATH + "3.jpg";

    private final static String IMAGES_PATH = CURRENT_PATH + "images\\";

    public static void main(String[] args) throws IOException {
        Workbook workBook = Workbook.getInstance(50);
        Sheet sheet = workBook.createSheet("测试");

        // 给标题行加上背景色，加颜色时，会对字体加粗
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setFgColorRgb("66cc66");
        // 图片数组
        File[] files = FileUtil.ls(IMAGES_PATH);
        UserPicture userPicture;
        for (int r = 0; r < 101; r++) {
            userPicture = new UserPicture();
            userPicture.setAge(15);
            userPicture.setName("测试-" + r);
            if(new Random().nextInt(10) > 5){
                // 模拟场景1. 在业务数据集合中，有些数据没有图片，有图片时请传图片绝对路径，没有图片时则不设置值
                userPicture.setPicture(IMG_PATH_1);
            }
            userPicture.setHeaderPicture(IMG_PATH_2);
            if(new Random().nextInt(10) > 5){
                // 模拟场景2. 在业务数据集合中，有些数据没有图片对应的图片集合，没有则不设置值
                userPicture.setPictures(getPictures(files, new Random().nextInt(10)));
            }
            sheet.createRow(userPicture);
            // 对标题行添加上样式
            if (r == 0) {
                sheet.getRow(r).setCellStyle(cellStyle);
            }
        }
        WebUtil.writeExcelTest(workBook, "模拟真实业务场景导出示例".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), TEMP_PATH);
    }

    /**
     * 根据图片数组和要获取图片的数量，随机从图片数组中取出若干
     *
     * @param files    图片数组
     * @param getCount 获取图片的数量
     * @return
     */
    private static List<String> getPictures(File[] files, int getCount) {
        List<String> list = new ArrayList<>(getCount);
        for (int i = 0; i < getCount; i++) {
            int index = new Random().nextInt(files.length);
            list.add(files[index].getAbsolutePath());
        }
        return list;
    }

}
