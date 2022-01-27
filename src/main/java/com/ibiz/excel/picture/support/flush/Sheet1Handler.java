package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.*;
import com.ibiz.excel.picture.support.util.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 图片
 *
 * @auther 喻场
 * @date 2020/7/618:33
 */
public class Sheet1Handler implements InvocationHandler {
	private IRepository target;

	private StylesIndex stylesIndex = new StylesIndex();

	public Sheet1Handler(IRepository proxy) {
		this.target = proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Sheet sheet = (Sheet) args[0];
		if (method.getName().equals("write")) {
			List<Row> rows = sheet.getRows();
			if (!rows.isEmpty()) {
				//未刷新过说明没有写入过流,这里主要为了写表头
				//如果写过了,则从脚标1开始,原因是为了对比合并单元格在row1中保存上一次刷新的最后一条数据
				int subIndex = !sheet.hasFlush() ? 0 : 1;
				this.setMergeCell(sheet, rows);
				//设置宽度
				this.setColumnWidth(sheet, subIndex);
				rows.subList(subIndex, rows.size()).stream().forEach(r -> writeSheetXML(r));
			}
		} else if (method.getName().equals("close")) {
			setEndSheetData();
			setMergeContent(sheet);
		}
		return method.invoke(target, args);
	}

	/**
	 * 设置列宽度
	 *
	 * @param sheet
	 * @param subIndex
	 */
	private void setColumnWidth(Sheet sheet, int subIndex) {
		if (subIndex == 0) {
			StringBuilder content = new StringBuilder();
			content.append("<cols>")
					// 默认宽度
					.append("<col min=\"1\" max=\"4\" width=\"" + WorkbookConstant.CELL_WEIGHT+ "\"/>");
			// 自定义的宽度
			sheet.getColumnHelperMap().entrySet().forEach(column ->
					content.append("<col min=\"")
					.append(column.getKey())
					.append("\" max=\"")
					.append(column.getKey())
					.append("\" width=\"")
					.append(column.getValue().getWidth())
					.append("\" customWidth=\"1\"/>"));
			content.append("</cols><sheetData>");
			target.append(content.toString());
		}
	}

	/**
	 * 增加合并列内容
	 *
	 * @param sheet
	 */
	private void setMergeContent(Sheet sheet) {
		Set<String> colCells = sheet.getColCells();
		StringBuilder content = new StringBuilder();
		if (sheet.isAutoMergeCell() && !sheet.getMergeCells().isEmpty()) {
			content.append("<mergeCells count=\"").append(sheet.getMergeCells().size()).append("\">");
			sheet.getMergeCells().stream().forEach(m -> {
				if (CollectionUtils.isEmpty(colCells)) {
					//增加合并行内容
					Cell start = new Cell(m.getFirstRow(), m.getFirstCol());
					Cell end = new Cell(m.getLastRow(), m.getLastCol());
					content.append("<mergeCell ref=\"")
							.append(start.getColNumber())
							.append(":")
							.append(end.getColNumber())
							.append("\"/>");
				} else {
					colCells.forEach(c -> content
							.append("<mergeCell ref=\"")
							.append(c)
							.append(m.getFirstRow())
							.append(":")
							.append(c)
							.append(m.getLastRow())
							.append("\"/>"));
				}
			});
			content.append("</mergeCells>");
		}
		target.append(content.toString());
	}

	/**
	 * 列结尾
	 */
	private void setEndSheetData() {
		target.append("</sheetData>");
	}

	private void setMergeCell(Sheet sheet, List<Row> rows) {
		String oldValue = "";
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.get(i);
			if (null == row) {
				continue;
			}
			List<Cell> cells = row.getCells();
			int mergeCellNumber = sheet.getMergeCellNumber();
			if (mergeCellNumber >= cells.size()) {
				//合并列超出范围,不进行合并
				break;
			}
			Cell cell = cells.get(mergeCellNumber);
			if (StringUtils.isNotBlank(oldValue) && oldValue.equals(cell.getValue())) {
				MergeCell mergeCell = null;
				if (!sheet.getMergeCells().isEmpty()) {
					mergeCell = sheet.getMergeCells().getLast();
				}
				if (null == mergeCell) {
					mergeCell = new MergeCell();
				}
				int endRowNumber = mergeCell.getLastRow();
				if (row.getRowNumber() == endRowNumber) {
					//与上一个合并对象合并
					mergeCell.setLastRow(++endRowNumber);
				} else {
					mergeCell = new MergeCell().setFirstRow(row.getRowNumber()).setLastRow(row.getRowNumber() + 1);
					sheet.getMergeCells().add(mergeCell);
				}
			}
			oldValue = StringUtils.isBlank(cell.getValue()) ? "" : cell.getValue();
		}
	}

	private void writeSheetXML(Row row) {
		StringBuilder content = new StringBuilder();
		//customHeight=1 使用自定义高度
		content.append("<row r=\"").append(row.getRowNumber() + 1).append("\" ht=\"").append(row.getHeight())
				.append("\" customHeight=\"1\"").append(" spans=\"1:").append(row.getCells().size()).append("\">");
		CellStyle cellStyle = row.getCellStyle();
		if(cellStyle != null){
			// 设置cellStyle样式下标
			stylesIndex.addCellStyle(cellStyle);
		}
		row.getCells().forEach(c -> content.append("<c r=\"")
				.append(c.getColNumber())
				.append("\" s=\"")
				.append(Optional.ofNullable(cellStyle)
						.map(CellStyle::getS).orElse(1))
				.append("\" t=\"s\">")
				.append("<v>")
				.append(c.getColDataNumber())
				.append("</v></c>")
		);
		content.append("</row>");
		target.append(content.toString());
	}

}
