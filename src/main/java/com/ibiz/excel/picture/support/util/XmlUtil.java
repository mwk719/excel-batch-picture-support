package com.ibiz.excel.picture.support.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @author MinWeikai
 * @date 2023-07-07 18:55:10
 * @description xml操作工具类
 */
public class XmlUtil {

    /**
     * 对象转xml字符串
     * @param object
     * @param formatted 是否格式化
     * @param fragment 是否不生成XML声明
     * @param <T>
     * @return
     * @throws JAXBException
     */
    public static <T> String objectToXmlStr(T object, boolean formatted, boolean fragment) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        // 格式化
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);
        // 生成XML声明
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }
}