package com.ibiz.excel.picture.support.listener;

import com.ibiz.excel.picture.support.flush.IRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 喻场
 * @date 2020/7/311:32
 */
public abstract class AbstractContentListener implements ContentListener {
    protected List<IRepository> repositories = new ArrayList<>();

    @Override
    public void addRepository(IRepository repository) {
        repositories.add(repository);
    }
}
