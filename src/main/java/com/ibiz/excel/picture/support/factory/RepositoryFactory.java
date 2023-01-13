package com.ibiz.excel.picture.support.factory;

import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.flush.Drawing1Handler;
import com.ibiz.excel.picture.support.flush.DrawingXmlRelsHandler;
import com.ibiz.excel.picture.support.flush.IRepository;
import com.ibiz.excel.picture.support.flush.Repository;
import com.ibiz.excel.picture.support.flush.SharedStringXmlHandler;
import com.ibiz.excel.picture.support.flush.Sheet1Handler;
import com.ibiz.excel.picture.support.flush.StylesHandler;
import com.ibiz.excel.picture.support.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @auther 喻场
 * @date 2020/7/619:07
 */
public class RepositoryFactory {

    public static IRepository getNewInstance(String alisa, File file, String content, String endContent, boolean writeXmlHead) throws IOException {
        Repository instance = new Repository();
        String name = file.getName();
        if (name.contains(".")) {
            //加这个判断因为media是一个目录,不是文件
            instance.setWrite(new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8)));
        }
        instance.setFile(file);
        instance.setWriteXmlHead(writeXmlHead);
        instance.append(content);
        instance.setEndContent(endContent);
        IRepository repository = instance;
        if (StringUtils.equals(alisa, Alias.DRAWING1_XML_RELS)) {
            repository = (IRepository) Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new DrawingXmlRelsHandler(instance));
        } else if (StringUtils.equals(alisa, Alias.DRAWING1)) {
            repository = (IRepository) Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new Drawing1Handler(instance));
        } else if (StringUtils.equals(alisa, Alias.SHARED_STRING_XML)) {
            repository = (IRepository) Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new SharedStringXmlHandler(instance));
        } else if (StringUtils.equals(alisa, Alias.SHEET1)) {
            repository = (IRepository) Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new Sheet1Handler(instance));
        } else if (StringUtils.equals(alisa, Alias.STYLES)) {
            repository = (IRepository) Proxy.newProxyInstance(RepositoryFactory.class.getClassLoader(), new Class[]{IRepository.class}, new StylesHandler(instance));
        }
        return repository;
    }

}
