package com.jpamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动
 * @Description
 * @Copyright Copyright (c) 2024
 * @author xieyubin
 * @since 2024-02-24 18:19:37
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class JpampApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpampApplication.class, args);
    }
}
