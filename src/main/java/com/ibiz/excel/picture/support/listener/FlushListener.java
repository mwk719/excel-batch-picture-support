package com.ibiz.excel.picture.support.listener;

import com.ibiz.excel.picture.support.model.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther 喻场
 * @date 2020/7/310:46
 */
public class FlushListener extends AbstractContentListener {
    @Override
    public void invoke(Sheet sheet) {
        repositories.forEach(r -> r.write(sheet));
    }
}
