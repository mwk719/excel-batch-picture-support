package com.ibiz.excel.picture.support.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author MinWeikai
 * @date 2022/1/7 18:09
 */
public class BaseJunitTest {

    protected static final String CURRENT_PATH = "E:\\test\\";
    protected static final String TEMP_PATH = CURRENT_PATH + "excel\\";
    protected final static String IMG_PATH = "E:\\test\\img\\";

    protected final static String IMG_PATH_1 = IMG_PATH + "1.jpg";
    protected final static String IMG_PATH_2 = IMG_PATH + "2.jpg";
    protected final static String IMG_PATH_3 = IMG_PATH + "3.jpg";

    protected final static String IMAGES_PATH = CURRENT_PATH + "images\\";

    protected final static List<String> urls = new ArrayList<>();

    static {
        urls.add("https://portrait.gitee.com/uploads/avatars/user/552/1657608_mwk719_1641537497.png");
        urls.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F1114%2F060421091316%2F210604091316-6-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644142768&t=82062e20360f72b0fd8d5fd7e2fc9885");
        urls.add("https://img2.baidu.com/it/u=121102239,1207969661&fm=253&fmt=auto&app=120&f=JPEG?w=1195&h=500");
        urls.add("https://img2.baidu.com/it/u=2602880481,728201544&fm=26&fmt=auto");
    }


    /**
     * 根据图片数组和要获取图片的数量，随机从图片数组中取出若干
     * @param files 图片数组
     * @param getCount 获取图片的数量
     * @return
     */
    protected static List<String> getPictures(File[] files, int getCount) {
        List<String> list = new ArrayList<>(getCount);
        for (int i = 0; i < getCount; i++) {
            int index = new Random().nextInt(files.length);
            list.add(files[index].getAbsolutePath());
        }
        return list;
    }

    protected static String getUrl() {
        int index = new Random().nextInt(urls.size());
        return urls.get(index);
    }
}
