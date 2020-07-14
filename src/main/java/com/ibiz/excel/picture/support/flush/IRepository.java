package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.Sheet;

import java.io.File;

/**
 * @auther 喻场
 * @date 2020/7/618:45
 */
public interface IRepository {
    //写入文件,并清空buff
    void write(Sheet sheet);
    //写入buff,关闭流
    void close(Sheet sheet);
    //增加字节
    void append(String content);
    //获取文件
    File getFile();
}
