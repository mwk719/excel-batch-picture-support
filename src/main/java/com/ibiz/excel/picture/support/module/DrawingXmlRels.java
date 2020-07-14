package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.constants.Alias;

/**
 * DrawingXmlRels sort less than drawing1
 * 先写入图片获得图片id后，后再填充drawing1图片信息
 * @auther 喻场
 * @date 2020/7/310:19
 */
@AutoWrite(sort = Integer.MIN_VALUE, dir = "xl" + WorkbookConstant.FILE_SEPARATOR + "drawings"+ WorkbookConstant.FILE_SEPARATOR +"_rels")
public class DrawingXmlRels {

    @AutoFile(fileName = "drawing1.xml.rels", alias = Alias.DRAWING1_XML_RELS, xmlEnd = AutoXmlHeadEndContent.RELS_END)
    String content = AutoXmlHeadEndContent.RELS_HEAD;
}
