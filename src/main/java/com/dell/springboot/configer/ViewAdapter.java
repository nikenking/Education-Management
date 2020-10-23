package com.dell.springboot.configer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpSession;

@Configuration
public class ViewAdapter extends WebMvcConfigurerAdapter {
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/addStudent").setViewName("addStudent");
            }
        };
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**");//拦截所有请求
        registry.addInterceptor(new LoginHandlerInceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login","/loginCheck");//排除掉
//        registry.addInterceptor(new TeacherHandlerInceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/t/1","/login","/loginCheck");

    }
}
