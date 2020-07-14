package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.Cell;
import com.ibiz.excel.picture.support.model.Row;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 图片
 * @auther 喻场
 * @date 2020/7/618:33
 */
public class SharedStringXmlHandler implements InvocationHandler {
    private IRepository target;
    public SharedStringXmlHandler(IRepository proxy) {
        this.target = proxy;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("write")) {
            Sheet sheet = (Sheet)args[0];
            List<Row> rows = sheet.getRows();
            if (!rows.isEmpty()) {
                //未刷新过说明没有写入过流,这里主要为了写表头
                //如果写过了,则从脚标1开始,原因是为了对比合并单元格在row1中保存上一次刷新的最后一条数据
                int subIndex = !sheet.hasFlush() ? 0 : 1;
                //从index=1开始,原因是保证与下一次第一条数据对比，进行合并
                rows.subList(subIndex, rows.size()).stream().flatMap(r -> r.getCells().stream()).forEach(c -> writeSharedStringXML(sheet, c));
            }
        }
        return method.invoke(target, args);
    }

    private void writeSharedStringXML(Sheet sheet, Cell cell) {
        StringBuffer sb = new StringBuffer();
        String cellValue = StringUtils.isBlank(cell.getValue()) ? "" : cell.getValue();
        sb.append("<si><t><![CDATA[").append(cellValue).append("]]></t></si>");
        target.append(sb.toString());
        sheet.setSharedStringSequence(sheet.getSharedStringSequence() + 1);
        cell.setColDataNumber(sheet.getSharedStringSequence());
    }
}
