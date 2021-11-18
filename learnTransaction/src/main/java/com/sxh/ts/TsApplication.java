package com.sxh.ts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类-模拟数据库连接
 * @author sxh
 * @date 2021/11/16
 */
@MapperScan(value = "com.sxh.ts.automatic")
@SpringBootApplication
public class TsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TsApplication.class, args);
    }
}
