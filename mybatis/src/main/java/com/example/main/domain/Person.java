package com.example.main.domain;

import com.example.main.common.SEX;

/**
 * @author gavin
 * @date 2019/4/1 19:05
 */
public class Person {
    private String name;
    private Long id;
    private Integer age;
    private SEX sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
