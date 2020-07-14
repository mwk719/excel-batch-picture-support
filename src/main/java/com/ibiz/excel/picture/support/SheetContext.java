package com.ibiz.excel.picture.support;

import com.ibiz.excel.picture.support.annotation.AutoFile;
import com.ibiz.excel.picture.support.annotation.AutoWrite;
import com.ibiz.excel.picture.support.annotation.InjectSheet;
import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;
import com.ibiz.excel.picture.support.event.WorkbookEvent;
import com.ibiz.excel.picture.support.factory.RepositoryFactory;
import com.ibiz.excel.picture.support.flush.IRepository;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.module.RelationShip;
import com.ibiz.excel.picture.support.util.ClassScanUtil;
import com.ibiz.excel.picture.support.util.CovertUtil;
import com.ibiz.excel.picture.support.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 单个Sheet上下文
 * @auther 喻场
 * @date 2020/7/313:51
 */
public class SheetContext {

    private final Sheet sheet;
    private final String BASE_PACKAGE = "com.ibiz.excel.picture.support.module";
    private SheetContext(Sheet sheet){
        this.sheet = sheet;
    }
    //key:alias value:ListenerRepository
    private ConcurrentMap<String, IRepository> repositoryHolder =  new ConcurrentHashMap<>();

    public ConcurrentMap<String, IRepository> getRepositoryHolder() {
        return repositoryHolder;
    }

    private List<WorkbookEvent> events = new ArrayList<>();
    public static SheetContext getInstance(Sheet sheet) {
        SheetContext context = new SheetContext(sheet);
        context.init();
        return context;
    }

    public List<WorkbookEvent> getEvents() {
        return events;
    }

    //初始化所有文件流,位置module下
    private void init() {
        //获取所有资源类
        List<Class> classes = ClassScanUtil.scan(BASE_PACKAGE, AutoWrite.class);
        classes.stream().sorted(Comparator.comparing(c -> ((AutoWrite)c.getAnnotation(AutoWrite.class)).sort())).forEach(c -> {
            try {
                initRepository(c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initRepository(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        AutoWrite annotation = clazz.getAnnotation(AutoWrite.class);
        //父目录
        final String dir = annotation.dir();
        File parentFile = FileUtil.createDir(sheet.getWorkbook().getWorkbookFile(), dir);
        Object obj = clazz.newInstance();
        //获取所有属性
        Field[] fields = clazz.getDeclaredFields();
        if (null != fields) {
            //注入sheet
            Arrays.asList(fields).stream().filter(f -> null != f.getAnnotation(InjectSheet.class)).forEach(f -> {
                f.setAccessible(true);
                try {
                    f.set(obj, sheet);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            Arrays.asList(fields).stream().filter(f -> null != f.getAnnotation(AutoFile.class)).forEach(f -> {
                f.setAccessible(true);
                AutoFile autoFile = f.getAnnotation(AutoFile.class);
                String alias = autoFile.alias();
                File finalFile = createFile(parentFile, autoFile);
                try {
                    repositoryHolder.put(alias, createRepository(alias, f.get(obj), finalFile, autoFile.xmlEnd(), autoFile.writeXmlHead()));
                } catch (IllegalAccessException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.asList(declaredMethods).stream().filter(m -> null != m.getAnnotation(AutoFile.class)).forEach(m -> {
            m.setAccessible(true);
            AutoFile autoFile = m.getAnnotation(AutoFile.class);
            String alias = autoFile.alias();
            File finalFile = createFile(parentFile, autoFile);
            try {
                repositoryHolder.put(alias, createRepository(alias, m.invoke(obj), finalFile, autoFile.xmlEnd(), autoFile.writeXmlHead()));
            } catch (IllegalAccessException | IOException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private File createFile(File parentFile, AutoFile autoFile) {
        //子目录
        String subDir = autoFile.subDir();
        //文件名
        String fileName = autoFile.fileName();
        File finalFile = parentFile;
        if (null != subDir) {
            //创建子目录
            finalFile = FileUtil.createDir(parentFile, subDir);
        }
        if (null != fileName) {
            //创建文件
            finalFile = FileUtil.createFile(finalFile, fileName);
        }
        return finalFile;
    }

    private IRepository createRepository(String alias, Object content, File finalFile, String xmlEnd, boolean writeXmlHead) throws IOException {
        String ct = "";
        if (content instanceof String) {
            ct = (String)content;
        } else if (content instanceof ArrayList) {
            ct = covert((ArrayList)content);
        }
        IRepository repository = RepositoryFactory.getNewInstance(alias, finalFile, ct, xmlEnd, writeXmlHead);
        return repository;
    }

    //暂时只处理RelationShip
    private String covert(List list) {
        StringBuffer content = new StringBuffer();
        if (null != list) {
            Object o = list.get(0);
            boolean covert = false;
            if (o instanceof RelationShip) {
                covert = true;
            }
            if (covert) {
                content.append(AutoXmlHeadEndContent.RELS_HEAD);
                list.stream().forEach(t -> content.append(CovertUtil.covert((RelationShip)t)));
            }
        }
        return content.toString();
    }

}
