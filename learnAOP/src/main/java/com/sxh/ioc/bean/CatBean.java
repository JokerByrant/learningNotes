package com.sxh.ioc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sxh
 * @date 2021/11/23
 */
@Configuration
public class CatBean {
    @Bean
    public Cat cat() {
        return new Cat("布偶", "white", 1);
    }
}
