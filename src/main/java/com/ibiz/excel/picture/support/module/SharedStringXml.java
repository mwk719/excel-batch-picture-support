package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.InjectSheet;
import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;
import com.ibiz.excel.picture.support.model.Sheet;

/**
 * SharedStringXml sort less than Sheet1
 * @auther 喻场
 * @date 2020/7/318:45
 */
@AutoWrite(sort = Integer.MIN_VALUE, dir = "xl")
public class SharedStringXml {
    @InjectSheet
    Sheet sheet;
    //count不影响文件,后续可以在写结尾时把count赋值
    @AutoFile(fileName = "sharedStrings.xml", alias = Alias.SHARED_STRING_XML, xmlEnd = AutoXmlHeadEndContent.SHARED_STRING_END)
    String content = "<sst xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" count=\"" + 0 +"\">";
}
