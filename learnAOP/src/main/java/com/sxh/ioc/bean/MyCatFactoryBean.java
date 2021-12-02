package com.sxh.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 定义一个初始化Cat的FactoryBean
 * @author sxh
 * @date 2021/12/2
 */
public class MyCatFactoryBean implements FactoryBean<Cat> {
    @Override
    public Cat getObject() throws Exception {
        return new Cat("旺财", "橘黄", 0);
    }

    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }
}
