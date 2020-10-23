package com.dell.springboot.configer;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidResource {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource DruidDataSource(){
        return new DruidDataSource();
    }
}
