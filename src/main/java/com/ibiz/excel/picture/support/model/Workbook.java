package com.ibiz.excel.picture.support.model;

import cn.hutool.core.util.ZipUtil;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.OutputStream;
import java.util.*;

/**
 * @auther 喻场
 * @date 2020/7/217:26
 */
public class Workbook {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 操作窗口
     * 当写入excel数据行数大于flushSize时{@link Sheet.SheetHandler#createRow(int)},
     * 会刷新数据到流,调用该方法
     * {@link  com.ibiz.excel.picture.support.flush.DrawingXmlRelsHandler#copyPictureAppendDrawingRelsXML(Sheet, Picture)}
     * 将图片刷新在磁盘中
     * 不会占用内存空间
     * flushSize = -1 时不刷新流
     */
    private int flushSize;
    /**
     * 工作表sheet
     */
    private List<Sheet> sheets;

    public File getWorkbookFile() {
        return workbookFile;
    }

    private final File workbookFile;
    /**
     * excel临时目录名
     */
    private final String filePath;
    /**
     * 生成的excel文件绝对路径
     */
    private final String destPath;
    /**执行了close方法后,才会有dest*/
    /**
     * 目的文件
     */
    private File destFile;
    private boolean close;
    /***
     * 一个excel文件的所有image被所有sheet公用
     * 图片缓存,key为图片字节流md5 ,  value为图片生成的编号
     */
    private Map<String, Integer> imageCache = new HashMap<>();

    public Map<String, Integer> getImageCache() {
        return imageCache;
    }

    private Workbook() {
        this(100);
    }

    private Workbook(int flushSize) {
        this.flushSize = flushSize;
        filePath = WorkbookConstant.AUTO_DIR + WorkbookConstant.FILE_SEPARATOR + UUID.randomUUID().toString().replace("-", "");
        destPath = WorkbookConstant.AUTO_DIR + WorkbookConstant.FILE_SEPARATOR + UUID.randomUUID().toString().replace("-", "") + WorkbookConstant.DEST_FILE_NAME_SUFFIX;
        this.workbookFile = FileUtil.createDir(null, filePath);
        sheets = new ArrayList<>();
    }

    /**
     * 获取WorkBook实例，默认100行刷新
     *
     * @return
     */
    public static Workbook getInstance() {
        return getInstance(100);
    }

    /**
     * 操作窗口
     * 当写入excel数据行数大于flushSize时{@link Sheet.SheetHandler#createRow(int)},
     * 会刷新数据到流,调用该方法
     * {@link  com.ibiz.excel.picture.support.flush.DrawingXmlRelsHandler#copyPictureAppendDrawingRelsXML(Sheet, Picture)}
     * 将图片刷新在磁盘中
     * 不会占用内存空间
     * flushSize = -1 时不刷新流
     * @param flushSize
     * @return
     */
    public static Workbook getInstance(int flushSize) {
        return new Workbook(flushSize);
    }

    public Sheet createSheet() {
        Sheet sheet = Sheet.getInstance(flushSize, this);
        sheets.add(sheet);
        return sheet;
    }

    public Sheet createSheet(String sheetName) {
        Sheet sheet = Sheet.getInstance(flushSize, sheetName, this);
        sheets.add(sheet);
        return sheet;
    }

    private void write() {
        if (sheets.isEmpty()) {
            throw new RuntimeException("none sheet has create");
        }
        sheets.forEach(Sheet::close);
        //把文件打包成xlsx
        ZipUtil.zip(filePath, destPath);
        destFile = new File(destPath);
        deleteFile(filePath);
        logger.info("dest excel path:{}", destPath);
    }

    public void close() {
        if (!close) {
            deleteFile(destPath);
            logger.info("delete dest excel path:{}", destPath);
        }
        close = true;
    }

    public void write(OutputStream output) {
        checkClose();
        write();
        FileUtil.writeToOutput(destFile, output);
    }

    private void deleteFile(String path) {
        File file = new File(path);
        FileUtil.delete(file);
    }

    private void checkClose() {
        if (close) {
            throw new RuntimeException("workbook is closed");
        }
    }
}
