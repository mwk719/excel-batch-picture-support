package com.ibiz.excel.picture.support.factory;

import com.ibiz.excel.picture.support.listener.ContentListener;

/**
 * @auther 喻场
 * @date 2020/7/311:52
 */
public class ListenerFactory {

    public static ContentListener getInstance(Class<? extends ContentListener> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("不支持的ContentListener:" + clazz, e);
        }
    }
}
