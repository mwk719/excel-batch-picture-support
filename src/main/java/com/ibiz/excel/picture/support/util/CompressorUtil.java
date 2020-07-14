package com.ibiz.excel.picture.support.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * 将文件夹压缩成文件
 */
public class CompressorUtil {
    /**
     *
     * @param src 原文件绝对路径
     * @param dest 目标文件绝对路径
     */
    public static void compress(String src, String dest) {
        //目的文件
        File destFile = new File(dest);
        //原文件
        File srcFile = new File(src);
        if (!srcFile.exists()){
            throw new RuntimeException(src + "不存在！");
        }
        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(destFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcFile);
        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
        //fileSet.setExcludes(...); //排除哪些文件或文件夹
        zip.addFileset(fileSet);
        zip.execute();
    }
}

