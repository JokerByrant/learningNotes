package com.sxh.io;

import lombok.Data;

import java.io.Serializable;

/**
 * 学生对象，继承Serializable，使其可以被序列化
 * @author 一池春水倾半城
 * @date 2019/12/5
 */
@Data
public class Student implements Serializable{
    private String name;

    // 在属性前加transient关键字，则其不会被序列化
    private transient String pwd;

    private Integer age;

    public Student(String name, String pwd, Integer age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }
}
