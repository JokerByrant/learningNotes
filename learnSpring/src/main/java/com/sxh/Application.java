package com.sxh;

import com.sxh.handler.DemoTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author sxh
 * @date 2021/3/19
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        DemoTest demoTest = applicationContext.getBean(DemoTest.class);
        demoTest.fun();
    }
}
