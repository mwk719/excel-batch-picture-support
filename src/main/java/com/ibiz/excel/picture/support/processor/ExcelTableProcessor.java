package com.ibiz.excel.picture.support.processor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * excel表格处理器
 * 简化并辅助解决动态表头导出问题
 *
 * @author MinWeikai
 * @date 2022/2/9 11:22
 */
public class ExcelTableProcessor {

    public ExcelTableProcessor(Sheet sheet) {
        this.sheet = sheet;
    }

    public static ExcelTableProcessor sheet(Sheet sheet) {
        return new ExcelTableProcessor(sheet);
    }

    /**
     * 行高
     */
    private int rowHeight = WorkbookConstant.PICTURE_ROW_HEIGHT;

    private final Sheet sheet;

    /**
     * 开始行号
     */
    private int startRow = 0;

    /**
     * 开始单元格
     */
    private int startCell = 0;

    /**
     * 样式
     */
    private final List<CellStyle> cellStyles = new ArrayList<>();

    /**
     * key : 对象的field属性名称
     * value : 此属性value相关的数据字典
     */
    private final Map<String, Map<String, String>> fieldEnumMap = new HashMap<>();

    /**
     * 构建excel
     *
     * @param excels excel属性配置，会根据excels中的field属性反射取出list中的值
     * @param list   需要导出的集合对象 该对象必须实现接口{@link BizExcelPojoInterface}
     */
    public void buildExcel(List<BizExcelRel> excels, List<? extends BizExcelPojoInterface> list) {
        // 开始行放标题
        Row row = sheet.createRow(startRow).setCellStyle(getCellStyle(startRow, null));
        List<Cell> cells = new ArrayList<>();
        BizExcelRel rel;
        excels = excels.stream().sorted(Comparator.comparing(BizExcelRel::getOrderNo)).collect(Collectors.toList());
        for (int i = startCell; i < excels.size(); i++) {
            rel = excels.get(i);
            cells.add(new Cell(i).setValue(rel.getExcelName()).setCellStyle(getCellStyle(startRow, i)));
            // 设置单元格宽度
            if (rel.getCellWeight() > 0) {
                sheet.setColumnWidth(rel.getOrderNo(), rel.getCellWeight());
            }
        }
        row.autoRowCells(cells);
        int num;
        BizExcelPojoInterface excelPojoInterface;
        Map<Integer, CellStyle> cellStyleMap = new HashMap<>();
        for (int j = 0; j < list.size(); j++) {
            // 开始行的下一行放内容
            num = startRow + j + 1;
            row = sheet.createRow(num).setCellStyle(getCellStyle(num, null));
            cells = new ArrayList<>();
            int index = startCell;
            excelPojoInterface = list.get(j);
            for (BizExcelRel excel : excels) {
                Object propertyValue = BeanUtil.getFieldValue(excelPojoInterface, excel.getField());
                String value;
                // 是图片
                if (excel.isPicture() && Objects.nonNull(propertyValue)) {
                    // 添加图片
                    PictureProcessor.build(sheet).addPictures(row, index, propertyValue,
                            0, WorkbookConstant.PICTURE_WEIGHT, WorkbookConstant.PICTURE_HEIGHT);
                    value = "";
                } else {
                    value = propertyValue == null ? "" : propertyValue.toString();
                }
                row.setHeight(rowHeight);
                // 数据字典值获取
                value = getValueByFiledEnumMap(excel.getField(), value);
                // 设置以下数据样式
                CellStyle cellStyle = getCellStyle(num, index);
                Cell cell = new Cell(num, index++).setValue(value);
                if (cellStyle != null && cellStyle.isColLast()) {
                    cellStyleMap.put(index, cellStyle);
                }

                CellStyle style = cellStyleMap.get(index);
                if (style != null) {
                    style = BeanUtil.copyProperties(style, CellStyle.class);
                }else {
                    style = cellStyle;
                }
                cells.add(cell.setCellStyle(style));
                row.setCells(cells);
            }

        }
    }

    private CellStyle getCellStyle(Integer row, Integer col) {
        return this.cellStyles.stream()
                .filter(c -> Objects.equals(c.getRowNumber(), row) && Objects.equals(col, c.getColNumber()))
                .findFirst().orElse(null);
    }

    /**
     * 数据字典值获取
     *
     * @param field
     * @param value
     * @return
     */
    private String getValueByFiledEnumMap(String field, String value) {
        Map<String, String> enumMap = fieldEnumMap.get(field);
        if (MapUtil.isNotEmpty(enumMap)) {
            return enumMap.get(value);
        }
        return value;
    }

    /**
     * 设置行高
     *
     * @param rowHeight
     * @return
     */
    public ExcelTableProcessor setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
        return this;
    }

    /**
     * 批量添加样式
     *
     * @param cellStyles
     * @return
     */
    public ExcelTableProcessor addCellStyle(List<CellStyle> cellStyles) {
        this.cellStyles.addAll(cellStyles);
        return this;
    }

    /**
     * 添加样式
     *
     * @param cellStyle
     * @return
     */
    public ExcelTableProcessor addCellStyle(CellStyle cellStyle) {
        this.cellStyles.add(cellStyle);
        return this;
    }

    /**
     * 设置写入数据开始行号
     *
     * @param startRow
     * @return
     */
    public ExcelTableProcessor setStartRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    /**
     * 设置写入数据开始单元格
     *
     * @param startCell
     * @return
     */
    public ExcelTableProcessor setStartCell(int startCell) {
        this.startCell = startCell;
        return this;
    }

    /**
     * 注入属性字典值
     *
     * @param field   属性名
     * @param enumMap 数据字典值
     * @return
     */
    public ExcelTableProcessor registryEnumMap(String field, Map<String, String> enumMap) {
        fieldEnumMap.put(field, enumMap);
        return this;
    }
}
