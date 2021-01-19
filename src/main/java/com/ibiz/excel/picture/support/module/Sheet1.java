package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;

/**
 * @auther 喻场
 * @date 2020/7/318:37
 */
@AutoWrite(dir = "xl")
public class Sheet1 {
    @AutoFile(subDir = "worksheets", fileName = "sheet1.xml", alias = Alias.SHEET1,
            xmlEnd = AutoXmlHeadEndContent.SHEET1_END)
    String content = "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" " +
            "xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" mc:Ignorable=\"x14ac\" xmlns:x14ac=\"http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac\" " +
            "><dimension ref=\"A1:L2\"/><sheetViews><sheetView tabSelected=\"1\" topLeftCell=\"A1\" workbookViewId=\"0\">" +
            "<selection activeCell=\"E19\" sqref=\"E19\"/></sheetView></sheetViews><sheetFormatPr defaultColWidth=\"20\" defaultRowHeight=\"13.5\" x14ac:dyDescent=\"0.15\"/>";

}
