package com.ibiz.excel.picture.support.listener;

import com.ibiz.excel.picture.support.flush.IRepository;
import com.ibiz.excel.picture.support.model.Sheet;

/**
 * @auther 喻场
 * @date 2020/7/310:44
 */
public interface ContentListener {
    void addRepository(IRepository repository);
    void invoke(Sheet sheet);
}
