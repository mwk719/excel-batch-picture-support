package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.Row;
import com.ibiz.excel.picture.support.model.Sheet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 设置样式
 *
 * @author MinWeikai
 * @date 2021/12/10 16:55
 */
public class StylesHandler implements InvocationHandler {
    private IRepository target;

    public StylesHandler(IRepository proxy) {
        this.target = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("write")) {
            Sheet sheet = (Sheet) args[0];
            List<Row> rows = sheet.getRows();
            if (!rows.isEmpty()) {
                // TODO 待办。。

            }


        }
        return method.invoke(target, args);
    }
}
