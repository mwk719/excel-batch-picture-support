package com.ibiz.excel.picture.support.pojo;

import com.ibiz.excel.picture.support.model.BizExcelPojoInterface;

import java.util.List;

/**
 * @author MinWeikai
 * @date 2022/2/9 9:21
 */
public class Student implements BizExcelPojoInterface {

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, String headPicture) {
        this.name = name;
        this.age = age;
        this.headPicture = headPicture;
    }

    public Student(String name, Integer age, String headPicture, List<String> album, Integer performance) {
        this.name = name;
        this.age = age;
        this.headPicture = headPicture;
        this.album = album;
        this.performance = performance;
    }

    private String name;

    private Integer age;

    private String headPicture;

    /**
     * 相册
     */
    private List<String> album;

    /**
     * 表现 0一般；1良好；2优秀
     */
    private Integer performance;

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public List<String> getAlbum() {
        return album;
    }

    public void setAlbum(List<String> album) {
        this.album = album;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
