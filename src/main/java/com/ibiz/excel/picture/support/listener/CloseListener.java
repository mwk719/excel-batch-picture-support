package com.ibiz.excel.picture.support.listener;

import com.ibiz.excel.picture.support.model.Sheet;

/**
 * @auther 喻场
 * @date 2020/7/310:46
 */
public class CloseListener extends AbstractContentListener {
    @Override
    public void invoke(Sheet sheet) {
        repositories.forEach(r -> {
            r.close(sheet);
        });
    }
}
