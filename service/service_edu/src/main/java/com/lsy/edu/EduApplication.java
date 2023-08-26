package com.lsy.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author liusy
 * @Date 2023/8/25 21:04
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lsy"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class);
    }
}
