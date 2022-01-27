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
        return "<styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">";

    }
}
