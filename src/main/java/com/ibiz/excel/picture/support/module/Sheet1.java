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
            "xmlns:xdr=\"http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing\" xmlns:x14=\"http://schemas.microsoft.com/office/spreadsheetml/2009/9/main\" " +
            "xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\"><sheetPr/><dimension ref=\"A1:AG8\"/><sheetViews><sheetView tabSelected=\"1\" workbookViewId=\"0\">" +
            "<selection activeCell=\"D3\" sqref=\"D3\"/></sheetView></sheetViews><sheetFormatPr defaultColWidth=\"20\" defaultRowHeight=\"130\" outlineLevelRow=\"10\"/><cols>" +
            "<col min=\"1\" max=\"4\" width=\"23.5\" customWidth=\"20\"/></cols><sheetData>";
}
