package com.sxh.ioc.context;

import com.sxh.ioc.bean.Cat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过xml来定义Bean，注入xml中的Bean
 * @author sxh
 * @date 2021/11/23
 */
public class _ClassPathXmlApplicationContext {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        Cat cat = (Cat) context.getBean("cat");
        System.out.println(cat);
    }
}
