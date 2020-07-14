package com.ibiz.excel.picture.support.event;

import com.ibiz.excel.picture.support.listener.ContentListener;
import com.ibiz.excel.picture.support.model.Sheet;

/**
 * @auther 喻场
 * @date 2020/7/818:52
 */
public class AbstractEvent implements WorkbookEvent {
    private ContentListener listener;
    public WorkbookEvent registry(ContentListener listener) {
        this.listener = listener;
        return this;
    }
    @Override
    public void onEvent(Sheet sheet) {
        listener.invoke(sheet);
    }
}
