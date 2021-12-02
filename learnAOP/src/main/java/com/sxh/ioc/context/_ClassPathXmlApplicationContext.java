package com.sxh.ioc.context;

import com.sxh.ioc.bean.Cat;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
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

        // ApplicationContext内部持有一个实例化的BeanFactory --- DefaultListableBeanFactory，通过向下转型拿到该实例后，可以在程序运行时往Spring容器中注册新的bean
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        defaultListableBeanFactory.registerSingleton("cat1", new Cat("布偶", "白色", 1));
        Cat cat1 = (Cat) context.getBean("cat1");
        System.out.println(cat1);

        // 获取通过FactoryBean注册的Bean
        Cat catFactoryBean = (Cat) context.getBean("catFactoryBean");
        System.out.println(catFactoryBean);
    }
}
