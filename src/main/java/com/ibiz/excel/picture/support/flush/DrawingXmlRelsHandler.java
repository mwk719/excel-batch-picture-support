package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.util.CovertUtil;
import com.ibiz.excel.picture.support.constants.Alias;
import com.ibiz.excel.picture.support.constants.WorkbookConstant;
import com.ibiz.excel.picture.support.model.Picture;
import com.ibiz.excel.picture.support.model.Sheet;
import com.ibiz.excel.picture.support.module.RelationShip;
import com.ibiz.excel.picture.support.util.FileUtil;
import com.ibiz.excel.picture.support.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

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
                    File file = new File(p.getPicturePath());
                    if(file.exists()) {
                        //写图片
                        copyPictureAppendDrawingRelsXML(sheet, p);
                    }
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
        File media = sheet.getSheetContext().getRepositoryHolder().get(Alias.MEDIA).getFile();
        int drawingSequence = sheet.getDrawingSequence();
        File destPicture = new File(media, "image"+ drawingSequence + ".png");
        File srcPicture = new File(picture.getPicturePath());
        try {
            FileUtil.copyFile(srcPicture, destPicture);
            picture.setRembed(drawingSequence);
            RelationShip relationShip = new RelationShip("rId" + drawingSequence, WorkbookConstant.MEDIA_IMAGE_TYPE, "../media/image" + drawingSequence +".png");
            drawingSequence++;
            sheet.setDrawingSequence(drawingSequence);
            target.append(CovertUtil.covert(relationShip));
        } catch (IOException e) {
            logger.error("图片copy到media目录下异常",e);
        }catch (Exception e) {
            logger.error("图片copy到media目录下异常",e);
        }
    }
}
