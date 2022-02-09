package com.ibiz.excel.picture.support.pojo;

import com.ibiz.excel.picture.support.model.BizExcelPojoInterface;

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

    private String name;

    private Integer age;

    private String headPicture;

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
