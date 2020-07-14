package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.InjectSheet;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.model.Sheet;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther 喻场
 * @date 2020/7/218:47
 */
@AutoWrite(dir = "docProps")
public class DocProps {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @AutoFile(fileName = "custom.xml", alias = Alias.CUSTOM)
    final String customContent = "<Properties xmlns=\"http://schemas.openxmlformats.org/officeDocument/2006/custom-properties\" xmlns:vt=\"http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes\">" +
            "<property fmtid=\"{D5CDD505-2E9C-101B-9397-08002B2CF9AE}\" pid=\"2\" name=\"KSOProductBuildVer\"><vt:lpwstr>2052-10.1.0.5973</vt:lpwstr></property></Properties>";

    @AutoFile(fileName = "core.xml", alias = Alias.CORE)
    String getCoreContent() {
        return "<cp:coreProperties xmlns:cp=\"http://schemas.openxmlformats.org/package/2006/metadata/core-properties\" " +
                "xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:dcmitype=\"http://purl.org/dc/dcmitype/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "<dc:creator>CODE GENERATION</dc:creator><dcterms:created xsi:type=\"dcterms:W3CDTF\">" + format.format(new Date()) +"</dcterms:created>" +
                "<dcterms:modified xsi:type=\"dcterms:W3CDTF\">" + format.format(new Date()) + "</dcterms:modified></cp:coreProperties>";
    }

    @AutoFile(fileName = "app.xml", alias = Alias.APP)
    String getAppContent() {
        return "<Properties xmlns=\"http://schemas.openxmlformats.org/officeDocument/2006/extended-properties\" xmlns:vt=\"http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes\">" +
                "<Application>CODE GENERATION</Application><HeadingPairs><vt:vector size=\"2\" baseType=\"variant\">" +
                "<vt:variant><vt:lpstr>工作表</vt:lpstr></vt:variant><vt:variant><vt:i4>1</vt:i4></vt:variant></vt:vector></HeadingPairs>\n" +
                "<TitlesOfParts><vt:vector size=\"1\" baseType=\"lpstr\"><vt:lpstr>" + sheet.getSheetName() + "</vt:lpstr></vt:vector></TitlesOfParts></Properties>";
    }
    @InjectSheet
    Sheet sheet;
}
