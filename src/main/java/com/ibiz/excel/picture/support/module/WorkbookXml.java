package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.InjectSheet;
import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.model.Sheet;

/**
 * @auther 喻场
 * @date 2020/7/317:48
 */
@AutoWrite(dir = "xl")
public class WorkbookXml {
    @AutoFile(fileName = "workbook.xml", alias = Alias.WORKBOOK_XML)
    String getAppContent() {
        return "<workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">" +
                "<fileVersion appName=\"xl\" lastEdited=\"3\" lowestEdited=\"5\" rupBuild=\"9302\"/><workbookPr/><bookViews><workbookView windowWidth=\"19095\" windowHeight=\"12210\"/>" +
                "</bookViews><sheets><sheet name=\""+ sheet.getSheetName() +"\" sheetId=\"1\" r:id=\"rId1\"/></sheets><calcPr calcId=\"144525\"/></workbook>";
    }
    @InjectSheet
    Sheet sheet;

}
