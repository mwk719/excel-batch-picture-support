package com.ibiz.excel.picture.support;

import com.ibiz.excel.picture.support.annotation.ExportModel;

import java.util.StringJoiner;

/**
 * @auther 喻场
 * @date 2020/7/813:41
 */
public class User {

    @ExportModel(sort = 0,title = "姓名", mergeMaster = true)
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
    @ExportModel(sort = 7, title = "部门1", merge = true)
    private String department1;
    @ExportModel(sort = 8, title = "部门2", merge = true)
    private String department2;
    @ExportModel(sort = 9, title = "部门3", merge = true)
    private String department3; @ExportModel(sort = 10, title = "部门4", merge = true)
    private String department4; @ExportModel(sort = 11, title = "部门5", merge = true)
    private String department5; @ExportModel(sort = 12, title = "部门6", merge = true)
    private String department6; @ExportModel(sort = 13, title = "部门7", merge = true)
    private String department7;

    public String getDepartment1() {
        return department1;
    }

    public void setDepartment1(String department1) {
        this.department1 = department1;
    }

    public String getDepartment2() {
        return department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2;
    }

    public String getDepartment3() {
        return department3;
    }

    public void setDepartment3(String department3) {
        this.department3 = department3;
    }

    public String getDepartment4() {
        return department4;
    }

    public void setDepartment4(String department4) {
        this.department4 = department4;
    }

    public String getDepartment5() {
        return department5;
    }

    public void setDepartment5(String department5) {
        this.department5 = department5;
    }

    public String getDepartment6() {
        return department6;
    }

    public void setDepartment6(String department6) {
        this.department6 = department6;
    }

    public String getDepartment7() {
        return department7;
    }

    public void setDepartment7(String department7) {
        this.department7 = department7;
    }

    public User(String name, Integer age, String department, String picture) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("department='" + department + "'")
                .add("picture='" + picture + "'")
                .toString();
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