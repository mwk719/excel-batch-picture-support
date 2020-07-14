package com.ibiz.excel.picture.support.flush;

import com.ibiz.excel.picture.support.constants.AutoXmlHeadEndContent;
import com.ibiz.excel.picture.support.model.Sheet;

import java.io.*;

/**
 * 存储excel组件文件流、内容
 * @auther 喻场
 * @date 2020/7/311:27
 */
public class Repository implements IRepository {
    private boolean hasWriteXmlHead = false;
    public Repository(){}

    private void clearContent() {
        content.setLength(0);
    }

    public void write(Sheet sheet) {
        try {
            if (null == write) {
                return;
            }
            writeToStream();
        } catch (Exception e) {
            throw new RuntimeException("write file error :" + file.getAbsolutePath(), e);
        }
    }

    private void writeToStream() {
        try {
            if (!hasWriteXmlHead && writeXmlHead) {
                write.write(AutoXmlHeadEndContent.XML_HEAD);
                hasWriteXmlHead = true;
            }
            write.write(content.toString());
            clearContent();
        } catch (IOException e) {
            throw new RuntimeException("write file error :" + file.getAbsolutePath(), e);
        }
    }

    @Override
    public void append(String content) {
        this.content.append(content);
    }

    public void close(Sheet sheet) {
        if (null == write) {
            return;
        }
        try {
            //把缓存中剩余的字节写入流
            writeToStream();
            write.write((null == endContent ? "" : endContent));
            write.flush();
            write.close();
        } catch (IOException e) {
            if (null != write) {
                try {
                    write.close();
                } catch (IOException ex) {
                    throw new RuntimeException("close write buff error", ex);
                }
            }
        }
    }
    //文件流
    private BufferedWriter write;
    //每行内容
    private StringBuffer content = new StringBuffer();
    //结尾内容
    private String endContent;
    //文件
    private File file;
    private boolean writeXmlHead;

    public File getFile() {
        return file;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public void setWrite(BufferedWriter write) {
        this.write = write;
    }

    public void setEndContent(String endContent) {
        this.endContent = endContent;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setWriteXmlHead(boolean writeXmlHead) {
        this.writeXmlHead = writeXmlHead;
    }
}
