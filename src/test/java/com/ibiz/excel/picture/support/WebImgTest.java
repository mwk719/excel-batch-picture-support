package com.ibiz.excel.picture.support;

import cn.hutool.http.HttpUtil;

import java.io.File;

/**
 * 网络图片测试
 *
 * @author MinWeikai
 * @date 2022/1/7 16:34
 */
public class WebImgTest {

    public static void main(String[] args) {
        String url = "https://portrait.gitee.com/uploads/avatars/user/552/1657608_mwk719_1641537497.png";
        File destFilePath = new File("E:\\test\\img\\1111.png");
        long destFile = HttpUtil.downloadFile(url, destFilePath, 5 * 1000);
        System.out.println(destFile);
    }
}
