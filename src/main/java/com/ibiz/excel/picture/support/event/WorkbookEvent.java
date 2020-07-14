package com.ibiz.excel.picture.support.event;

import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.listener.ContentListener;

public interface WorkbookEvent<E extends ContentListener> {
    WorkbookEvent registry(E listener);
    void onEvent(Sheet sheet);
}
