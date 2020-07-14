package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 喻场
 * @date 2020/7/217:57
 */
@AutoWrite
public class RelationShipGroup {
    public RelationShipGroup() {
        init();
    }
    private void init() {
        masterRels.add(new RelationShip("rId1", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument", "xl/workbook.xml"));
        masterRels.add(new RelationShip("rId3", "http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties", "docProps/core.xml"));
        masterRels.add(new RelationShip("rId2", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties", "docProps/app.xml"));
        masterRels.add(new RelationShip("rId4", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/custom-properties", "docProps/custom.xml"));
        workbookXmlRels.add(new RelationShip("rId4", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings", "sharedStrings.xml"));
        workbookXmlRels.add(new RelationShip("rId3", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles", "styles.xml"));
        workbookXmlRels.add(new RelationShip("rId2", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "theme/theme1.xml"));
        workbookXmlRels.add(new RelationShip("rId1", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet", "worksheets/sheet1.xml"));
        sheetRels.add(new RelationShip("rId1", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/drawing", "../drawings/drawing1.xml"));
    }
    //_rels/.rels
    @AutoFile(subDir = "_rels", fileName = ".rels", alias = Alias.RELS_RELS,
            xmlEnd = AutoXmlHeadEndContent.RELS_END)
    public List<RelationShip> masterRels = new ArrayList<>();
    //xl/_rels/workbook.xml.rels
    @AutoFile(subDir = "xl" + WorkbookConstant.FILE_SEPARATOR +"_rels", fileName = "workbook.xml.rels", alias = Alias.WORKBOOK_XML_RELS,
            xmlEnd = AutoXmlHeadEndContent.RELS_END)
    List<RelationShip> workbookXmlRels = new ArrayList<>();
    //xl/worksheets/_rels/sheet1.xml.rels
    @AutoFile(subDir = "xl" + WorkbookConstant.FILE_SEPARATOR +"worksheets" + WorkbookConstant.FILE_SEPARATOR + "_rels", fileName = "sheet1.xml.rels", alias = Alias.SHEET1_XML_RELS,
            xmlEnd = AutoXmlHeadEndContent.RELS_END)
    List<RelationShip> sheetRels = new ArrayList<>();
}
