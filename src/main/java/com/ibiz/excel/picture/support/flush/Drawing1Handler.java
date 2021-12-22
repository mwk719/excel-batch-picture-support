package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.Picture;
import com.ibiz.excel.picture.support.model.Sheet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 适应单元格填充多图
 *
 * @author MinWeikai
 * @date 2021-01-19 18:53:06
 */
public class Drawing1Handler implements InvocationHandler {
	private IRepository target;

	/**
	 * 容器间距 默认10000，可能约等于1px吧
	 */
	private static final int PADDING = 10000;

	public Drawing1Handler(IRepository proxy) {
		this.target = proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("write")) {
			Sheet sheet = (Sheet) args[0];
			Map<Integer, List<Picture>> listMap = sheet.getPictures().stream()
					.collect(Collectors.groupingBy(e -> e.getFromCol() + e.getFromRow()));

			for (Map.Entry<Integer, List<Picture>> map : listMap.entrySet()) {
				this.writerDrawing(map.getValue());
			}
		}
		return method.invoke(target, args);
	}

	private void writerDrawing(List<Picture> pictures) {
		Picture p;
		for (int i = 0; i < pictures.size(); i++) {
			p = pictures.get(i);
			if (p != null) {
				writerDrawingXML(p, i);
			}
		}
	}

	/**
	 * 写 drawing1.xml
	 *
	 * @param picture 图片
	 * @param i 第几张图片，从0开始
	 */
	private void writerDrawingXML(
			Picture picture, int i) {
		/*
		<xdr:from> 表示从xx开始
			<xdr:col>2</xdr:col> 第几个单元格
			<xdr:colOff>9525</xdr:colOff> 离左边框距离
			<xdr:row>2</xdr:row> 第几行
			<xdr:rowOff>9525</xdr:rowOff> 离上边框距离
		</xdr:from>
		<xdr:to> 表示到xx位置
			<xdr:col>2</xdr:col> 第几个单元格
			<xdr:colOff>1009525</xdr:colOff>
			<xdr:row>3</xdr:row> 第几行
			<xdr:rowOff>12225</xdr:rowOff> 离上边框距离
		</xdr:to>
		<xdr:pic> 图片标签
		</xdr:pic>
		 */
		String content = "<xdr:twoCellAnchor>" +
				"<xdr:from><xdr:col>" + picture.getFromCol() + "</xdr:col><xdr:colOff>";
		// 图片间距
		int colOff = i * picture.getWidth();
		// 默认从离左边框1个像素，防止图片遮挡边框线
		if(colOff == 0){
			colOff = PADDING;
		}
		content = content + colOff + "</xdr:colOff>" +
				"<xdr:row>" + picture.getFromRow() + "</xdr:row>" +
                "<xdr:rowOff>" + PADDING +
                "</xdr:rowOff>" +
                "</xdr:from>" +
				"<xdr:to>\n" +
				"<xdr:col>" + picture.getToCol() + "</xdr:col><xdr:colOff>" + (colOff + picture.getWidth() - PADDING) + "</xdr:colOff>" +
				"<xdr:row>" + (picture.getToRow()) +
				// 设置图片高度
				"</xdr:row><xdr:rowOff>"+ picture.getHeight() +"</xdr:rowOff>" +
				"</xdr:to>" +
				"<xdr:pic><xdr:nvPicPr>" +
				"<xdr:cNvPr id=\"" + (1 + i) + "\" name=\"Picture " + picture.getRembed() + "\" descr=\"Picture\"/><xdr:cNvPicPr><a:picLocks noChangeAspect=\"1\"/></xdr:cNvPicPr></xdr:nvPicPr><xdr:blipFill>" +
				"<a:blip r:embed=\"rId" + picture.getRembed() + "\"/>\n" +
				"<a:stretch><a:fillRect/></a:stretch></xdr:blipFill><xdr:spPr><a:xfrm><a:off x=\"3000000\" y=\"300000\"/><a:ext cx=\"1000000\" cy=\"1260000\"/></a:xfrm><a:prstGeom prst=\"rect\">\n" +
				"<a:avLst/></a:prstGeom></xdr:spPr>" +
				"</xdr:pic>" +
				"<xdr:clientData/></xdr:twoCellAnchor>";
		target.append(content);
	}

}
