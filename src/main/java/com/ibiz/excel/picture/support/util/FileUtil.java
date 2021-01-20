package com.ibiz.excel.picture.support.util;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

/**
 * @auther 喻场
 * @date 2020/7/615:50
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * @param parent 已创建的父目录
     * @param filePath 子目录/文件子路径
     * @return  File
     */
    public static File createFile(File parent, String filePath) {
        File file = new File(parent, filePath);
        return file;
    }

    public static File createDir(File parent, String filePath) {
        String[] subPath = filePath.split("\\" + WorkbookConstant.FILE_SEPARATOR);
        File file = null;
        for (String path : subPath) {
            file = new File(parent, path);
            if (!file.exists()) {
                file.mkdir();
            }
            parent = file;
        }
        return file;
    }

    public static void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            Arrays.stream(files).forEach(f -> delete(f));
            file.delete();
            return;
        }
        file.delete();
    }

    public static void writeToOutput(File source, OutputStream output) {
        byte[] buf = new byte[1024];
        int len;
        try(BufferedInputStream fis = new BufferedInputStream(new FileInputStream(source))) {
            while((len = fis.read(buf)) != -1){
                output.write(buf, 0 , len);
            }
        }catch (IOException e) {
            throw new RuntimeException("write to output error , destFile path:" + source.getAbsolutePath(), e);
        }
    }

    public static void copyFile(File source, File target) throws IOException {
        byte[] buf = new byte[1024];
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(target);
        while (in.read(buf) != -1) {
            out.write(buf);
        }
        in.close();
        out.close();
    }
}
