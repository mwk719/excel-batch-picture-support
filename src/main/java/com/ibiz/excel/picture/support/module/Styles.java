package com.ibiz.excel.picture.support.module;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.constants.Alias;

/**
 * 有时间可以优化
 * 电子表格样式参考处
 * http://officeopenxml.com/SSstyles.php
 *
 * @author MinWeikai
 * @date 2021-01-19 14:00:55
 */
@AutoWrite(dir = "xl")
public class Styles {
    @AutoFile(fileName = "styles.xml", alias = Alias.STYLES)
    String content() {
        return "<styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><numFmts count=\"4\"><numFmt numFmtId=\"44\" formatCode=\"_ &quot;￥&quot;* #,##0.00_ ;_ &quot;￥&quot;* \\-#,##0.00_ ;_ &quot;￥&quot;* &quot;-&quot;??_ ;_ @_ \"/>" +
                "<numFmt numFmtId=\"41\" formatCode=\"_ * #,##0_ ;_ * \\-#,##0_ ;_ * &quot;-&quot;_ ;_ @_ \"/><numFmt numFmtId=\"42\" formatCode=\"_ &quot;￥&quot;* #,##0_ ;_ &quot;￥&quot;* \\-#,##0_ ;_ &quot;￥&quot;* &quot;-&quot;_ ;_ @_ \"/><numFmt numFmtId=\"43\" formatCode=\"_ * #,##0.00_ ;_ * \\-#,##0.00_ ;_ * &quot;-&quot;??_ ;_ @_ \"/></numFmts>" +
                "<fonts count=\"23\">" +
                "<font><sz val=\"11\"/><color indexed=\"8\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color theme=\"1\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color theme=\"1\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><i/><sz val=\"11\"/><color rgb=\"FF7F7F7F\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"11\"/><color theme=\"3\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><u/><sz val=\"11\"/><color rgb=\"FF800080\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color rgb=\"FF9C0006\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"11\"/><color rgb=\"FFFFFFFF\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"13\"/><color theme=\"3\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color rgb=\"FFFF0000\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color theme=\"0\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"11\"/><color rgb=\"FF3F3F3F\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"15\"/><color theme=\"3\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"11\"/><color theme=\"1\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"18\"/><color theme=\"3\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><u/><sz val=\"11\"/><color rgb=\"FF0000FF\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color rgb=\"FF9C6500\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color rgb=\"FF3F3F76\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"11\"/><color rgb=\"FFFA7D00\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color rgb=\"FFFA7D00\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><sz val=\"11\"/><color rgb=\"FF006100\"/><name val=\"宋体\"/><charset val=\"0\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"10\"/><color theme=\"1\"/><name val=\"宋体\"/><family val=\"3\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>" +
                "<font><b/><sz val=\"20\"/><color theme=\"4\" tint=\"-0.249977111117893\"/><name val=\"华文楷体\"/><family val=\"3\"/><charset val=\"134\"/></font>" +
                "</fonts>" +
                "<fills count=\"36\">" +
                "<fill><patternFill patternType=\"none\"/></fill><fill><patternFill patternType=\"gray125\"/></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor indexed=\"65\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"C6E0B4\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"5\" /><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFFFC7CE\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"8\" tint=\"0.599993896298105\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"4\" tint=\"0.599993896298105\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFA5A5A5\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"6\" tint=\"0.599993896298105\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"8\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"6\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"4\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFF2F2F2\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFFFFFCC\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"9\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"7\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"5\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"9\" tint=\"0.399975585192419\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFFFEB9C\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"7\" tint=\"0.399975585192419\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"5\" tint=\"0.399975585192419\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFFFCC99\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"4\" tint=\"0.799981688894314\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"8\" tint=\"0.799981688894314\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"6\" tint=\"0.799981688894314\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"8\" tint=\"0.399975585192419\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"4\" tint=\"0.399975585192419\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"6\" tint=\"0.399975585192419\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"7\" tint=\"0.799981688894314\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"5\" tint=\"0.799981688894314\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FFC6EFCE\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"9\" tint=\"0.799981688894314\"/><bgColor indexed=\"64\"/></patternFill></fill>" +
                "<fill><patternFill patternType=\"solid\"><fgColor theme=\"6\" tint=\"0.39997558519241921\"/><bgColor indexed=\"5\"/></patternFill></fill>"; // 绿色背景，后续可在此追加

    }
}
