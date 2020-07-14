package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;

/**
 * media目录
 * @auther 喻场
 * @date 2020/7/39:25
 */
@AutoWrite(dir = "xl")
public class Media {
    @AutoFile(subDir = "media", alias = Alias.MEDIA)
    String dir;
}
