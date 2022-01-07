package com.ibiz.excel.picture.support.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件流md5
 * @author yc 创建时间：2018年1月24日 下午4:50:35
 */
public class MD5Digester {
    /**
     * digital
     */
    public static final char[] DIGITAL = "0123456789ABCDEF".toCharArray();
    /**
     * MD5Digester
     */
    private MD5Digester() {}

    public static String digestMD5(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        return digestMD5(fis);
    }
    /**
     * 文件流md5
     * @param inputStream InputStream
     * @return  md5
     */
    public static String digestMD5(InputStream inputStream) {
        try(InputStream is = inputStream) {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int len;
            while (-1 != (len = is.read(buffer))) {
                digest.update(buffer, 0, len);
            }
            byte[] md5hash = digest.digest();
            return encodeHexStr(md5hash);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * encodeHexStr
     * @param bytes bytes
     * @return String
     */
    private static String encodeHexStr(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        char[] result = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; ++i) {
            result[(i * 2)] = DIGITAL[((bytes[i] & 0xF0) >> 4)];
            result[(i * 2 + 1)] = DIGITAL[(bytes[i] & 0xF)];
        }
        return new String(result);
    }

    public static String encodeHexStr(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return encodeHexStr(bytes);
    }

}
