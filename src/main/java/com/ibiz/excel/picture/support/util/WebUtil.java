package com.ibiz.excel.picture.support.util;

import cn.hutool.core.io.FileUtil;
import com.ibiz.excel.picture.support.model.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * web文件处理工具
 *
 * @author MinWeikai
 * @date 2021-12-06 17:49:09
 */
public class WebUtil {

    private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

    /**
     * excel导出测试
     *
     * @param wb       自定义的excel工作簿excel
     * @param fileName 文件名
     * @param tempPath 生成文件存放临时路径
     */
    public static void writeExcelTest(Workbook wb, String fileName, String tempPath) {
        File file = FileUtil.touch(tempPath.concat(fileName));
        try (BufferedOutputStream os = FileUtil.getOutputStream(file)) {
            wb.write(os);
        } catch (Exception e) {
            // 报错时删除文件
            FileUtil.del(file);
            log.error("测试导出excel异常", e);
        }finally {
            wb.close();
        }
        log.debug("测试导出excel路径：{}", file.getAbsolutePath());
    }

    /**
     * excel web端下载
     *
     * @param wb       自定义的excel工作簿excel
     * @param fileName 文件名
     * @param response 响应体
     * @throws IOException
     */
    public static void writeExcel(Workbook wb, String fileName, HttpServletResponse response) throws IOException {
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        try (ServletOutputStream os = response.getOutputStream()) {
            wb.write(os);
        } catch (Exception e) {
            log.error("导出excel异常", e);
        } finally {
            wb.close();
        }
    }


}
