package com.ibiz.excel.picture.support.factory;

import com.ibiz.excel.picture.support.flush.*;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @auther 喻场
 * @date 2020/7/619:07
 */
public class RepositoryFactory {

    public static IRepository getNewInstance(String alisa, File file, String content, String endContent, boolean writeXmlHead) throws IOException {
        Repository instance = new Repository();
        String name = file.getName();
        if (name.indexOf(".") > -1) {
            //加这个判断因为media是一个目录,不是文件
            instance.setWrite(new BufferedWriter(new FileWriter(file)));
        }
        instance.setFile(file);
        instance.setWriteXmlHead(writeXmlHead);
        instance.append(content);
        instance.setEndContent(endContent);
        IRepository repository = instance;
        if (StringUtils.equals(alisa, Alias.DRAWING1_XML_RELS)) {
            repository = (IRepository)Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new DrawingXmlRelsHandler(instance));
        } else if (StringUtils.equals(alisa, Alias.DRAWING1)) {
            repository = (IRepository)Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new Drawing1Handler(instance));
        } else if (StringUtils.equals(alisa, Alias.SHARED_STRING_XML)) {
            repository = (IRepository)Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new SharedStringXmlHandler(instance));
        } else if (StringUtils.equals(alisa, Alias.SHEET1)) {
            repository = (IRepository)Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new Sheet1Handler(instance));
        }
        return repository;
    }

}
