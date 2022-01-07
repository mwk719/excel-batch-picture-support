package com.ibiz.excel.picture.support.util;

import java.io.File;

/**
 * @author MinWeikai
 * @date 2022/1/7 17:26
 */
public class MD5DigesterTest {

    public static void main(String[] args) {
        File destFilePath = new File("E:\\test\\img\\0.jpg");
        String md5 = null;
        md5 = MD5Digester.encodeHexStr("destFilePath");
        System.out.println(md5);
    }
}