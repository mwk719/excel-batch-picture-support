package com.ibiz.excel.picture.support.constants;

/**
 * @auther 喻场
 * @date 2020/7/218:42
 */
public class WorkbookConstant {
    public static final String AUTO_DIR = System.getProperty("java.io.tmpdir");
    public static final String FILE_SEPARATOR = "/";
    public static final String DEST_FILE_NAME_SUFFIX = ".xlsx";
    public static final String MEDIA_IMAGE_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image";
    /**有图片的行高设置为100*/
    public static final int PICTURE_ROW_HEIGHT = 100;

    /**
     * 图片默认高度
     */
    public static final int PICTURE_HEIGHT = 1260000;
    /**
     * 图片默认宽度
     */
    public static final int PICTURE_WEIGHT = 1500000;

    /**
     * 单元格宽度
     */
    public static final double CELL_WEIGHT = 23.5;

    /**
     * 下载网络图片超时时间
     */
    public static final int DOWNLOAD_PICTURE_TIME_OUT = 10 * 1000;
}
