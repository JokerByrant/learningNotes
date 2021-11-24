package com.sxh.ioc.context;

import com.sxh.ioc.bean.Cat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author sxh
 * @date 2021/11/24
 */
public class _FileSystemXmlApplicationContext {
    public static void main(String[] args) {
        // ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-bean.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("D:/spring-bean.xml");
        Cat cat = (Cat) context.getBean("cat");
        System.out.println(cat);
    }
}
