package com.ibiz.excel.picture.support;

import com.ibiz.excel.picture.support.annotation.ExportModel;

/**
 * @auther 喻场
 * @date 2020/7/813:41
 */
public class UserPicture {

    public UserPicture() {
    }

    @ExportModel(title = "姓名", mergeMaster = true)
    private String name;
    @ExportModel(sort = 1, title = "年龄")
    private Integer age;
    @ExportModel(sort = 2, title = "部门", merge = true)
    private String department;
    @ExportModel(sort = 3, isPicture = true, title = "图片1")
    private String picture;
    @ExportModel(sort = 4, isPicture = true, title = "图片2")
    private String picture1;
    @ExportModel(sort = 5, isPicture = true, title = "图片3")
    private String picture2;
    @ExportModel(sort = 6, isPicture = true, title = "图片4")
    private String picture3;
    @ExportModel(sort = 7, isPicture = true, title = "图片5")
    private String picture4;
    @ExportModel(sort = 8, isPicture = true, title = "图片6")
    private String picture5;
    @ExportModel(sort = 9, isPicture = true, title = "图片7")
    private String picture6;
    @ExportModel(sort = 10, isPicture = true, title = "图片8")
    private String picture7;
    @ExportModel(sort = 11, isPicture = true, title = "图片9")
    private String picture8;

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getPicture5() {
        return picture5;
    }

    public void setPicture5(String picture5) {
        this.picture5 = picture5;
    }

    public String getPicture6() {
        return picture6;
    }

    public void setPicture6(String picture6) {
        this.picture6 = picture6;
    }

    public String getPicture7() {
        return picture7;
    }

    public void setPicture7(String picture7) {
        this.picture7 = picture7;
    }

    public String getPicture8() {
        return picture8;
    }

    public void setPicture8(String picture8) {
        this.picture8 = picture8;
    }

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

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }
}
