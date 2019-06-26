package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Classname com.Application
 * @Description springboot启动类
 * @Date 2019/4/15 21:48
 * @Created by huang
 * @Author huang
 */
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
