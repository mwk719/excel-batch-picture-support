package com.ibiz.excel.picture.support.flush;

import cn.hutool.http.HttpUtil;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.constants.PictureSourceContent;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.Picture;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.module.RelationShip;
import com.ibiz.excel.picture.support.util.CovertUtil;
import com.ibiz.excel.picture.support.util.FileUtil;
import com.ibiz.excel.picture.support.util.MD5Digester;
import com.ibiz.excel.picture.support.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 图片
 *
 * @auther 喻场
 * @date 2020/7/618:33
 */
public class DrawingXmlRelsHandler implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private IRepository target;

    public DrawingXmlRelsHandler(IRepository proxy) {
        this.target = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("write")) {
            Sheet sheet = (Sheet) args[0];
            List<Picture> pictures = sheet.getPictures();
            pictures.stream().filter(Objects::nonNull).forEach(p -> {
                if (StringUtils.isNotBlank(p.getPicturePath())) {
                    //写图片
                    copyPictureAppendDrawingRelsXML(sheet, p);
                }
            });
        }
        return method.invoke(target, args);
    }

    /**
     * 把图片copy到media目录下
     *
     * @param sheet
     * @param picture
     */
    private void copyPictureAppendDrawingRelsXML(Sheet sheet, Picture picture) {
        String picturePath = picture.getPicturePath();
        // 根据图片来源获取文件md5值
        String md5;
        try {
            md5 = getMd5BySource(picture.getPictureSource(), picturePath);
        } catch (FileNotFoundException e) {
            logger.warn("获取不到路径为{}的图片", picturePath);
            // 获取不到时跳出
            return;
        }

        try {
            Integer drawingSequence = sheet.getWorkbook().getImageCache().get(md5);
            // 已存在的图片
            if (Objects.nonNull(drawingSequence)) {
                // 记录索引
                picture.setRembed(drawingSequence);
                return;
            }
            File media = sheet.getSheetContext().getRepositoryHolder().get(Alias.MEDIA).getFile();
            drawingSequence = sheet.getDrawingSequence();
            // 获取excel资源图片
            File destPicture = getExcelDestPicture(picture, picturePath, drawingSequence, media);
            // 输出的图片不存在则跳出
            if (!destPicture.exists()) {
                return;
            }
            picture.setRembed(drawingSequence);
            RelationShip relationShip = new RelationShip("rId" + drawingSequence, WorkbookConstant.MEDIA_IMAGE_TYPE, "../media/image" + drawingSequence + ".png");
            //先把drawingSequence放入缓存,因为从缓存中获取时设置drawingSequence再+1对应图片。实际图片的drawingSequence不变
            sheet.getWorkbook().getImageCache().put(md5, drawingSequence);
            drawingSequence++;
            sheet.setDrawingSequence(drawingSequence);
            target.append(CovertUtil.covert(relationShip));
        } catch (Exception e) {
            logger.error("图片copy到media目录下异常", e);
        }
    }

    /**
     * 获取excel资源图片
     * @param picture
     * @param picturePath
     * @param drawingSequence
     * @param media
     * @return
     * @throws IOException
     */
    private static File getExcelDestPicture(Picture picture, String picturePath, Integer drawingSequence, File media) throws IOException {
        File destPicture = new File(media, "image" + drawingSequence + ".png");
        if (PictureSourceContent.WEB_URL == picture.getPictureSource()) {
            // 下载网络图片到excel资源路径
            HttpUtil.downloadFile(picturePath, destPicture, WorkbookConstant.DOWNLOAD_PICTURE_TIME_OUT);
        } else {
            // 绝对路径图片
            File srcPicture = new File(picturePath);
            // 复制绝对图片到excel资源路径
            FileUtil.copyFile(srcPicture, destPicture);
        }
        return destPicture;
    }

    /**
     * 根据图片来源获取文件md5值
     *
     * @param pictureSource
     * @param picturePath
     * @return
     * @throws FileNotFoundException
     */
    private static String getMd5BySource(int pictureSource, String picturePath) throws FileNotFoundException {
        String md5;
        // 判断图片来源
        if (PictureSourceContent.WEB_URL == pictureSource) {
            md5 = MD5Digester.digestMD5(picturePath);
        } else {
            // 绝对路径图片
            File srcPicture = new File(picturePath);
            md5 = MD5Digester.digestMD5(srcPicture);
        }
        return md5;
    }
}
