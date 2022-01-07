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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 图片
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
            Sheet sheet = (Sheet)args[0];
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
     * @param sheet
     * @param picture
     */
    private void copyPictureAppendDrawingRelsXML(Sheet sheet, Picture picture) {
        try {
            String md5;
            File srcPicture = null;
            String picturePath = picture.getPicturePath();
            // 判断图片来源
            if(PictureSourceContent.WEB_URL == picture.getPictureSource() ){
                md5 = MD5Digester.encodeHexStr(picturePath);
            }else {
                // 绝对路径图片
                srcPicture = new File(picturePath);
                // 绝对路径下图片不存在则不执行
                if(!srcPicture.exists()){
                    return;
                }
                md5 = MD5Digester.digestMD5(srcPicture);
            }
            Integer drawingSequence = sheet.getWorkbook().getImageCache().get(md5);
            // 已存在的图片
            if (Objects.nonNull(drawingSequence)) {
                // 记录索引
                picture.setRembed(drawingSequence);
                return;
            }
            File media = sheet.getSheetContext().getRepositoryHolder().get(Alias.MEDIA).getFile();
            drawingSequence = sheet.getDrawingSequence();
            File destPicture = new File(media, "image"+ drawingSequence + ".png");
            if(PictureSourceContent.WEB_URL == picture.getPictureSource() ){
                // 下载网络图片到excel资源路径
                HttpUtil.downloadFile(picturePath, destPicture, WorkbookConstant.DOWNLOAD_PICTURE_TIME_OUT);
            }else {
                // 复制绝对图片到excel资源路径
                FileUtil.copyFile(srcPicture, destPicture);
            }
            // 输出的图片不存在则跳出
            if(!destPicture.exists()){
                return;
            }
            picture.setRembed(drawingSequence);
            RelationShip relationShip = new RelationShip("rId" + drawingSequence, WorkbookConstant.MEDIA_IMAGE_TYPE, "../media/image" + drawingSequence +".png");
            //先把drawingSequence放入缓存,因为从缓存中获取时设置drawingSequence再+1对应图片。实际图片的drawingSequence不变
            sheet.getWorkbook().getImageCache().put(md5, drawingSequence);
            drawingSequence++;
            sheet.setDrawingSequence(drawingSequence);
            target.append(CovertUtil.covert(relationShip));
        } catch (Exception e) {
            logger.error("图片copy到media目录下异常",e);
        }
    }
}
