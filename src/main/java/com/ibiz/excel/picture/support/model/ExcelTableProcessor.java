package com.ibiz.excel.picture.support.model;

import cn.hutool.core.bean.BeanUtil;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MinWeikai
 * @date 2022/2/9 11:22
 */
public class ExcelTableProcessor {

    public ExcelTableProcessor(Sheet sheet) {
        this.sheet = sheet;
    }

    public static ExcelTableProcessor build(Sheet sheet) {
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
     * 样式
     */
    private final Map<Integer, CellStyle> cellStyleMap = new HashMap<>();

    /**
     * 构建excel
     *
     * @param excels excel属性配置
     * @param list   需要导出的集合对象 该对象必须实现接口{@link BizExcelPojoInterface}
     */
    public void buildExcel(List<BizExcelRel> excels, List<? extends BizExcelPojoInterface> list) {
        // 开始行放标题
        Row row = sheet.createRow(startRow).setCellStyle(cellStyleMap.get(startRow));
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < excels.size(); i++) {
            BizExcelRel rel = excels.get(i);
            cells.add(new Cell(i).setValue(rel.getExcelName()));
            // 设置单元格宽度
            sheet.setColumnWidth(rel.getOrderNo(), rel.getCellWeight());
        }
        row.autoRowCells(cells);
        int num;
        List<Picture> pictures = sheet.getPictures();
        for (int j = 0; j < list.size(); j++) {
            // 开始行的下一行放内容
            num = startRow + j + 1;
            row = sheet.createRow(num).setCellStyle(cellStyleMap.get(num));
            cells = new ArrayList<>();
            int index = 0;
            BizExcelPojoInterface excelPojoInterface = list.get(j);
            excels = excels.stream().sorted(Comparator.comparing(BizExcelRel::getOrderNo)).collect(Collectors.toList());
            for (BizExcelRel excel : excels) {
                Object propertyValue = BeanUtil.getFieldValue(excelPojoInterface, excel.getField());
                String value = propertyValue == null ? "" : propertyValue.toString();
                // 是图片
                if (excel.isPicture() && StringUtils.isNotBlank(value)) {
                    pictures.add(new Picture(row.getRowNumber(), excel.getOrderNo() - 1, WorkbookConstant.PICTURE_WEIGHT, value));
                    value = "";
                }
                row.setHeight(rowHeight);
                cells.add(new Cell(num, index++).setValue(value));
                row.setCells(cells);
            }

        }
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
     */
    public void addCellStyle(List<CellStyle> cellStyles) {
        cellStyles.forEach(cellStyle-> this.cellStyleMap.put(cellStyle.getRowNumber(), cellStyle));
    }

    /**
     * 添加样式
     * @param cellStyle
     * @return
     */
    public ExcelTableProcessor addCellStyle(CellStyle cellStyle) {
        this.cellStyleMap.put(cellStyle.getRowNumber(), cellStyle);
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
}
