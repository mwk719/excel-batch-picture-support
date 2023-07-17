package com.ibiz.excel.picture.support.model.abt;

import com.ibiz.excel.picture.support.util.XmlUtil;

import javax.xml.bind.JAXBException;

/**
 * @author MinWeikai
 * @date 2023/7/13 10:57
 * @description Xml抽象处理
 */
public abstract class XmlAbstract implements XmlInterface {

    @Override
    public String toXmlString() {
        try {
            return XmlUtil.objectToXmlStr(this, false, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }
}
