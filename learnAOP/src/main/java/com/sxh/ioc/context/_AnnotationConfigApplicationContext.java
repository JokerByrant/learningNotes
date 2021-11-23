package com.sxh.ioc.context;

import com.sxh.ioc.bean.Cat;
import com.sxh.ioc.bean.CatBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过注解定义Bean，注入该Bean
 * @author sxh
 * @date 2021/11/23
 */
public class _AnnotationConfigApplicationContext {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CatBean.class);
        Cat cat = (Cat) context.getBean("cat");
        System.out.println(cat);
    }
}
