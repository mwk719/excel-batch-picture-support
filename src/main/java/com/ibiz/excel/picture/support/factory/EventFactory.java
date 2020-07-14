package com.ibiz.excel.picture.support.factory;

import com.ibiz.excel.picture.support.event.WorkbookEvent;

/**
 * @auther 喻场
 * @date 2020/7/319:24
 */
public class EventFactory {
    public static WorkbookEvent getInstance(Class<? extends WorkbookEvent> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("不支持的WorkbookEvent:" + clazz, e);
        }
    }
}
