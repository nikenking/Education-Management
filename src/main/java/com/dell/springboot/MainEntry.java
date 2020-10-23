package com.dell.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.dell.springboot.mapper")
@SpringBootApplication
public class MainEntry {
    public static void main(String[] args) {
        SpringApplication.run(MainEntry.class,args);
    }
}