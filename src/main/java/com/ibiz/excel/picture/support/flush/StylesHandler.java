package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.CellStyle;
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

    private final StringBuilder xfBefore = new StringBuilder("</fills><borders count=\"10\"><border><left/><right/><top/><bottom/><diagonal/></border><border><left style=\"thin\"><color auto=\"1\"/></left><right style=\"thin\"><color auto=\"1\"/></right><top style=\"thin\"><color auto=\"1\"/></top><bottom style=\"thin\"><color auto=\"1\"/></bottom><diagonal/></border><border><left style=\"double\"><color rgb=\"FF3F3F3F\"/></left><right style=\"double\"><color rgb=\"FF3F3F3F\"/></right><top style=\"double\">".concat(
            "<color rgb=\"FF3F3F3F\"/></top><bottom style=\"double\"><color rgb=\"FF3F3F3F\"/></bottom><diagonal/></border><border><left/><right/><top/><bottom style=\"medium\"><color theme=\"4\"/></bottom><diagonal/></border><border><left style=\"thin\"><color rgb=\"FF3F3F3F\"/></left><right style=\"thin\"><color rgb=\"FF3F3F3F\"/></right><top style=\"thin\"><color rgb=\"FF3F3F3F\"/></top><bottom style=\"thin\"><color rgb=\"FF3F3F3F\"/></bottom><diagonal/></border><border><left style=\"thin\"><color rgb=\"FFB2B2B2\"/></left><right style=\"thin\"><color rgb=\"FFB2B2B2\"/></right><top style=\"thin\"><color rgb=\"FFB2B2B2\"/></top><bottom style=\"thin\"><color rgb=\"FFB2B2B2\"/></bottom><diagonal/></border><border><left/><right/><top style=\"thin\"><color theme=\"4\"/></top><bottom style=\"double\"><color theme=\"4\"/></bottom><diagonal/></border><border><left/><right/><top/><bottom style=\"medium\">").concat(
            "<color theme=\"4\" tint=\"0.499984740745262\"/></bottom><diagonal/></border><border><left style=\"thin\"><color rgb=\"FF7F7F7F\"/></left><right style=\"thin\"><color rgb=\"FF7F7F7F\"/></right><top style=\"thin\"><color rgb=\"FF7F7F7F\"/></top><bottom style=\"thin\"><color rgb=\"FF7F7F7F\"/></bottom><diagonal/></border><border><left/><right/><top/><bottom style=\"double\"><color rgb=\"FFFF8001\"/></bottom><diagonal/></border></borders><cellStyleXfs count=\"49\"><xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"42\" fontId=\"1\" fillId=\"0\" borderId=\"0\" applyFont=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"26\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\">").concat(
            "<alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"17\" fillId=\"23\" borderId=\"8\" applyNumberFormat=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"44\" fontId=\"1\" fillId=\"0\" borderId=\"0\" applyFont=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"41\" fontId=\"1\" fillId=\"0\" borderId=\"0\" applyFont=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"10\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"6\" fillId=\"6\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\">").concat(
            "<alignment vertical=\"center\"/></xf><xf numFmtId=\"43\" fontId=\"1\" fillId=\"0\" borderId=\"0\" applyFont=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"29\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"15\" fillId=\"0\" borderId=\"0\" applyNumberFormat=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"9\" fontId=\"1\" fillId=\"0\" borderId=\"0\" applyFont=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"5\" fillId=\"0\" borderId=\"0\" applyNumberFormat=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\">").concat(
            "<alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"1\" fillId=\"15\" borderId=\"5\" applyNumberFormat=\"0\" applyFont=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"22\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"4\" fillId=\"0\" borderId=\"0\" applyNumberFormat=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"9\" fillId=\"0\" borderId=\"0\" applyNumberFormat=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"14\" fillId=\"0\" borderId=\"0\" applyNumberFormat=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\">").concat(
            "<alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"3\" fillId=\"0\" borderId=\"0\" applyNumberFormat=\"0\" applyFill=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"12\" fillId=\"0\" borderId=\"3\" applyNumberFormat=\"0\" applyFill=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"8\" fillId=\"0\" borderId=\"3\" applyNumberFormat=\"0\" applyFill=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"28\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"4\" fillId=\"0\" borderId=\"7\" applyNumberFormat=\"0\" applyFill=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"10\" fillId=\"21\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"11\" fillId=\"14\" borderId=\"4\" applyNumberFormat=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"18\" fillId=\"14\" borderId=\"8\" applyNumberFormat=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"7\" fillId=\"9\" borderId=\"2\" applyNumberFormat=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"33\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"18\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\">").concat(
            "<alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"19\" fillId=\"0\" borderId=\"9\" applyNumberFormat=\"0\" applyFill=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"13\" fillId=\"0\" borderId=\"6\" applyNumberFormat=\"0\" applyFill=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"20\" fillId=\"32\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"16\" fillId=\"20\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"25\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"10\" fillId=\"13\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"24\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"8\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"31\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"5\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"12\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\">").concat(
            "<alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"17\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"30\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"4\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"11\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"2\" fillId=\"7\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf><xf numFmtId=\"0\" fontId=\"10\" fillId=\"27\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"10\" fillId=\"16\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"2\" fillId=\"3\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"10\" fillId=\"19\" borderId=\"0\" applyNumberFormat=\"0\" applyBorder=\"0\" applyAlignment=\"0\" applyProtection=\"0\"><alignment vertical=\"center\"/></xf></cellStyleXfs>").concat(
            "<cellXfs count=\"3\">").concat(
            "<xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"><alignment vertical=\"center\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"0\" fillId=\"2\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyFill=\"1\" applyBorder=\"1\" applyAlignment=\"1\"><alignment horizontal=\"center\" vertical=\"center\" wrapText=\"1\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyBorder=\"1\" applyAlignment=\"1\"><alignment wrapText=\"1\"/></xf>").concat(
            "<xf numFmtId=\"0\" fontId=\"21\" fillId=\"3\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyAlignment=\"1\"><alignment horizontal=\"center\" vertical=\"center\"/></xf>").concat( // 填充色值坐标
            "<xf numFmtId=\"0\" fontId=\"22\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyAlignment=\"1\"><alignment horizontal=\"center\" vertical=\"center\"/></xf>"));

    private final StringBuilder xfAfter = new StringBuilder("</cellXfs>".concat(
            "<cellStyles count=\"49\"><cellStyle name=\"常规\" xfId=\"0\" builtinId=\"0\"/><cellStyle name=\"货币[0]\" xfId=\"1\" builtinId=\"7\"/><cellStyle name=\"20% - 强调文字颜色 3\" xfId=\"2\" builtinId=\"38\"/><cellStyle name=\"输入\" xfId=\"3\" builtinId=\"20\"/><cellStyle name=\"货币\" xfId=\"4\" builtinId=\"4\"/><cellStyle name=\"千位分隔[0]\" xfId=\"5\" builtinId=\"6\"/><cellStyle name=\"40% - 强调文字颜色 3\" xfId=\"6\" builtinId=\"39\"/><cellStyle name=\"差\" xfId=\"7\" builtinId=\"27\"/><cellStyle name=\"千位分隔\" xfId=\"8\" builtinId=\"3\"/><cellStyle name=\"60% - 强调文字颜色 3\" xfId=\"9\" builtinId=\"40\"/><cellStyle name=\"超链接\" xfId=\"10\" builtinId=\"8\"/><cellStyle name=\"百分比\" xfId=\"11\" builtinId=\"5\"/><cellStyle name=\"已访问的超链接\" xfId=\"12\" builtinId=\"9\"/><cellStyle name=\"注释\" xfId=\"13\" builtinId=\"10\"/><cellStyle name=\"60% - 强调文字颜色 2\" xfId=\"14\" builtinId=\"36\"/><cellStyle name=\"标题 4\" xfId=\"15\" builtinId=\"19\"/><cellStyle name=\"警告文本\" xfId=\"16\" builtinId=\"11\"/><cellStyle name=\"标题\" xfId=\"17\" builtinId=\"15\"/>").concat(
            "<cellStyle name=\"解释性文本\" xfId=\"18\" builtinId=\"53\"/><cellStyle name=\"标题 1\" xfId=\"19\" builtinId=\"16\"/><cellStyle name=\"标题 2\" xfId=\"20\" builtinId=\"17\"/><cellStyle name=\"60% - 强调文字颜色 1\" xfId=\"21\" builtinId=\"32\"/><cellStyle name=\"标题 3\" xfId=\"22\" builtinId=\"18\"/><cellStyle name=\"60% - 强调文字颜色 4\" xfId=\"23\" builtinId=\"44\"/><cellStyle name=\"输出\" xfId=\"24\" builtinId=\"21\"/><cellStyle name=\"计算\" xfId=\"25\" builtinId=\"22\"/><cellStyle name=\"检查单元格\" xfId=\"26\" builtinId=\"23\"/><cellStyle name=\"20% - 强调文字颜色 6\" xfId=\"27\" builtinId=\"50\"/><cellStyle name=\"强调文字颜色 2\" xfId=\"28\" builtinId=\"33\"/><cellStyle name=\"链接单元格\" xfId=\"29\" builtinId=\"24\"/><cellStyle name=\"汇总\" xfId=\"30\" builtinId=\"25\"/><cellStyle name=\"好\" xfId=\"31\" builtinId=\"26\"/><cellStyle name=\"适中\" xfId=\"32\" builtinId=\"28\"/><cellStyle name=\"20% - 强调文字颜色 5\" xfId=\"33\" builtinId=\"46\"/><cellStyle name=\"强调文字颜色 1\" xfId=\"34\" builtinId=\"29\"/><cellStyle name=\"20% - 强调文字颜色 1\" xfId=\"35\" builtinId=\"30\"/>").concat(
            "<cellStyle name=\"40% - 强调文字颜色 1\" xfId=\"36\" builtinId=\"31\"/><cellStyle name=\"20% - 强调文字颜色 2\" xfId=\"37\" builtinId=\"34\"/><cellStyle name=\"40% - 强调文字颜色 2\" xfId=\"38\" builtinId=\"35\"/><cellStyle name=\"强调文字颜色 3\" xfId=\"39\" builtinId=\"37\"/><cellStyle name=\"强调文字颜色 4\" xfId=\"40\" builtinId=\"41\"/><cellStyle name=\"20% - 强调文字颜色 4\" xfId=\"41\" builtinId=\"42\"/><cellStyle name=\"40% - 强调文字颜色 4\" xfId=\"42\" builtinId=\"43\"/><cellStyle name=\"强调文字颜色 5\" xfId=\"43\" builtinId=\"45\"/><cellStyle name=\"40% - 强调文字颜色 5\" xfId=\"44\" builtinId=\"47\"/><cellStyle name=\"60% - 强调文字颜色 5\" xfId=\"45\" builtinId=\"48\"/><cellStyle name=\"强调文字颜色 6\" xfId=\"46\" builtinId=\"49\"/><cellStyle name=\"40% - 强调文字颜色 6\" xfId=\"47\" builtinId=\"51\"/><cellStyle name=\"60% - 强调文字颜色 6\" xfId=\"48\" builtinId=\"52\"/></cellStyles><tableStyles count=\"0\" defaultTableStyle=\"TableStyleMedium2\"/></styleSheet>"));

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 因会写入磁盘，刷新sheet.getRows()，所以需要计算是否最后一块数据
        boolean last = true;
        if (method.getName().equals("write")) {
            Sheet sheet = (Sheet) args[0];
            List<Row> rows = sheet.getRows();
            if (!rows.isEmpty()) {
                rows.stream().forEach(row -> {
                    CellStyle cellStyle = row.getCellStyle();
                    if (row.getCellStyle() != null) {
                        // 追加背景色样式
                        this.appendFill(cellStyle);
                        // 追加调用下标样式
                        this.appendXf(cellStyle);
                    }
                });
            }

            int x = sheet.getRowCount() % sheet.getFlushSize();
            int y = rows.size() % sheet.getFlushSize();
            // 判断是否最后
            last = x - y != 1;
            // 只写入一条数据需要特殊判断
            boolean one = y == 0 && sheet.hasFlush();
            // 不是最后一块数据或者没有行数据，则不写入样式
            if (!last || rows.isEmpty() || one) {
                return "";
            }
            // 在Styles对象中追加样式
            target.append(xfBefore.append(xfAfter).toString());
        }
        return method.invoke(target, args);
    }

    /**
     * 追加调用下标样式
     *
     * @param cellStyle
     */
    private void appendXf(CellStyle cellStyle) {
        xfBefore.append("<xf numFmtId=\"0\" fontId=\"21\" fillId=\"")
                .append(cellStyle.getFillId())
                .append("\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyAlignment=\"1\"><alignment horizontal=\"center\" vertical=\"center\"/></xf>");
    }

    /**
     * 追加背景色样式
     *
     * @param cellStyle
     */
    private void appendFill(CellStyle cellStyle) {
        target.append("<fill><patternFill patternType=\"solid\"><fgColor rgb=\"".concat( cellStyle.getFgColorRgb()).concat( "\"/><bgColor indexed=\"64\"/></patternFill></fill>"));
    }

}
