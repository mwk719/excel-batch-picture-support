package com.ibiz.excel.picture.support;

import com.ibiz.excel.picture.support.annotation.ExportModel;

/**
 * @auther 喻场
 * @date 2020/7/813:41
 */
public class UserPicture {

    public UserPicture() {
    }

    @ExportModel(title = "姓名")
    private String name;
    @ExportModel(sort = 1, title = "年龄")
    private Integer age;
    @ExportModel(sort = 3, title = "部门")
    private String department;
    @ExportModel(sort = 2, isPicture = true, title = "图片1")
    private String picture;
    @ExportModel(sort = 4, isPicture = true, title = "图片2")
    private String headerPicture;

    public UserPicture(String name, Integer age, String department, String picture) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.picture = picture;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHeaderPicture() {
        return headerPicture;
    }

    public void setHeaderPicture(String headerPicture) {
        this.headerPicture = headerPicture;
    }
}
