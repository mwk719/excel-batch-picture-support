package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.model.Picture;
import com.ibiz.excel.picture.support.model.Sheet;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 图片
 * @auther 喻场
 * @date 2020/7/618:33
 */
public class Drawing1Handler implements InvocationHandler {
    private IRepository target;
    public Drawing1Handler(IRepository proxy) {
        this.target = proxy;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("write")) {
            Sheet sheet = (Sheet)args[0];
            List<Picture> pictures = sheet.getPictures();
            pictures.stream().filter(Objects::nonNull).forEach(p -> {
                writerDrawingXML(p);
            });
        }
        return method.invoke(target, args);
    }

    /**
     * 写 drawing1.xml
     * @param picture
     * @param picture
     */
    private void writerDrawingXML(
            Picture picture) {
        String content = "<xdr:twoCellAnchor><xdr:from><xdr:col>" + picture.getFromCol() +"</xdr:col><xdr:colOff>0</xdr:colOff>" +
                "<xdr:row>" + picture.getFromRow() + "</xdr:row><xdr:rowOff>0</xdr:rowOff></xdr:from><xdr:to>\n" +
                "<xdr:col>" + picture.getToCol() +"</xdr:col><xdr:colOff>9525</xdr:colOff>" +
                "<xdr:row>" + picture.getToRow() +"</xdr:row><xdr:rowOff>1260000</xdr:rowOff></xdr:to><xdr:pic><xdr:nvPicPr>" +
                "<xdr:cNvPr id=\"1\" name=\"Picture " + picture.getRembed() +"\" descr=\"Picture\"/><xdr:cNvPicPr><a:picLocks noChangeAspect=\"1\"/></xdr:cNvPicPr></xdr:nvPicPr><xdr:blipFill>" +
                "<a:blip r:embed=\"rId" + picture.getRembed() +"\"/>\n" +
                "<a:stretch><a:fillRect/></a:stretch></xdr:blipFill><xdr:spPr><a:xfrm><a:off x=\"3000000\" y=\"300000\"/><a:ext cx=\"1000000\" cy=\"1260000\"/></a:xfrm><a:prstGeom prst=\"rect\">\n" +
                "<a:avLst/></a:prstGeom></xdr:spPr></xdr:pic><xdr:clientData/></xdr:twoCellAnchor>";
        target.append(content);
    }

}
