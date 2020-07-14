package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;

/**
 * @auther 喻场
 * @date 2020/7/318:16
 */
@AutoWrite(dir = "xl")
public class Drawing1 {

    @AutoFile(subDir = "drawings", fileName = "drawing1.xml", alias = Alias.DRAWING1,
    xmlEnd = AutoXmlHeadEndContent.DRAWING_1_END)
    String content = AutoXmlHeadEndContent.DRAWING_1_HEAD;
}
