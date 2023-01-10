package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

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

    /**
     * 字体
     */
    private final StringBuilder fonts = new StringBuilder("<fonts count=\"23\">" +
            "<font><sz val=\"11\"/><color indexed=\"8\"/><name val=\"宋体\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>");


    /**
     * 填充色
     */
    private final StringBuilder fills = new StringBuilder("</fonts>" +
            "<fills count=\"36\">" +
            "<fill><patternFill patternType=\"none\"/></fill><fill><patternFill patternType=\"gray125\"/></fill>");


    /**
     * 样式调用
     */
    private final StringBuilder cellXfs = new StringBuilder(("</fills>" +
            "<borders count=\"2\">" +
            "<border><left/><right/><top/><bottom/><diagonal/></border>" +
            "<border><left style=\"thin\"><color indexed=\"64\"/></left><right style=\"thin\"><color indexed=\"64\"/></right><top style=\"thin\"><color indexed=\"64\"/></top><bottom style=\"thin\"><color indexed=\"64\"/></bottom><diagonal/></border>" +
            "</borders>" +
            "<cellStyleXfs count=\"1\"><xf borderId=\"0\" fillId=\"0\" fontId=\"0\" numFmtId=\"0\"/></cellStyleXfs>").concat(
            "<cellXfs count=\"3\">").concat(
            "<xf numFmtId=\"0\" fontId=\"0\" applyAlignment=\"1\" applyBorder=\"1\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"0\" applyAlignment=\"1\" applyBorder=\"1\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyFont=\"1\"><alignment horizontal=\"center\" vertical=\"center\"/></xf>")
    );

    private final StringBuilder cellStyles = new StringBuilder("</cellXfs>".concat(
            "<cellStyles count=\"1\"><cellStyle builtinId=\"0\" name=\"常规\" xfId=\"0\"/></cellStyles><tableStyles count=\"0\" defaultTableStyle=\"TableStyleMedium2\"/></styleSheet>"));

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("write")) {
            Sheet sheet = (Sheet) args[0];
            List<Row> rows = sheet.getRows();
            if (!rows.isEmpty()) {
                rows.forEach(row -> {
                    addCellStyle(row.getCellStyle());
                    row.getCells().forEach(cell -> addCellStyle(cell.getCellStyle()));
                });
            }
        }else if (method.getName().equals("close")) {
            // 关闭时将所有的样式追加在Styles对象中
            target.append(fonts.append(fills).append(cellXfs).append(cellStyles).toString());
        }
        return method.invoke(target, args);
    }

    private void addCellStyle(CellStyle cellStyle) {
        if (cellStyle != null) {
            // 追加字体
            this.appendFont(cellStyle.getFont());
            // 追加背景色样式
            this.appendFill(cellStyle);
            // 追加调用下标样式
            this.appendXf(cellStyle);
        }
    }

    /**
     * 追加调用下标样式
     *
     * @param cellStyle
     */
    private void appendXf(CellStyle cellStyle) {
        Font font = cellStyle.getFont();
        cellXfs.append("<xf numFmtId=\"0\" fontId=\"")
                .append(Optional.ofNullable(font).map(Font::getFontId).orElse(0))
                .append("\" fillId=\"")
                .append(cellStyle.getFillId())
                .append("\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyAlignment=\"1\"><alignment horizontal=\"center\" vertical=\"center\"/></xf>");
    }

    /**
     * 追加背景色样式
     *
     * @param cellStyle
     */
    private void appendFill(CellStyle cellStyle) {
        if(StringUtils.isNotBlank(cellStyle.getFgColorRgb())){
            fills.append("<fill><patternFill patternType=\"solid\"><fgColor rgb=\"").append(cellStyle.getFgColorRgb()).append("\"/><bgColor indexed=\"64\"/></patternFill></fill>");
        }
    }

    /**
     * 追加字体
     * @param font
     */
    private void appendFont(Font font) {
        if(font != null){
            fonts.append("<font><sz val=\"").append(font.getFontHeightInPoints())
                    .append("\"/>");
            // <b/> 加粗标志
            if(font.isBoldWeight()){
                fonts.append("<b/>");
            }
            fonts.append("<color ");
            if(StringUtils.isNotBlank(font.getColor())){
                fonts.append("rgb=\"").append(font.getColor()).append("\"");
            }else {
                fonts.append("indexed=\"8\"");
            }
            fonts.append("/><name val=\"")
                    .append(font.getFontName()).append("\"/><charset val=\"134\"/><scheme val=\"minor\"/></font>");
        }
    }

}
