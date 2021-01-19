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
	 * @param picture
	 * @param i
	 */
	private void writerDrawingXML(
			Picture picture, int i) {
		String content = "<xdr:twoCellAnchor>" +
				"<xdr:from><xdr:col>" + picture.getFromCol() + "</xdr:col><xdr:colOff>";
		int colOff = i * picture.getWidth();
		content = content + colOff + "</xdr:colOff>" +
				"<xdr:row>" + picture.getFromRow() + "</xdr:row><xdr:rowOff>0</xdr:rowOff></xdr:from>" +
				"<xdr:to>\n" +
				"<xdr:col>" + picture.getToCol() + "</xdr:col><xdr:colOff>" + (colOff + picture.getWidth()) + "</xdr:colOff>" +
				"<xdr:row>" + picture.getToRow() + "</xdr:row><xdr:rowOff>1260000</xdr:rowOff>" +
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
