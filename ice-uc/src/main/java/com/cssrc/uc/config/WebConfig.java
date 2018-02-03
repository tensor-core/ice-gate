package com.cssrc.uc.config;

import com.cssrc.common.handler.GlobalExceptionHandler;
import com.cssrc.uc.interceptor.ClientInterceptor;
import com.cssrc.uc.interceptor.JwtInterceptor;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        commonPathPatterns.add("/user/validate");
        commonPathPatterns.add("/user/front/info");
        commonPathPatterns.add("/user/front/menus");
        commonPathPatterns.add("/menu/all");

        //测试spring cloud stream 消息队列发送
        commonPathPatterns.add("/user/message1");
        commonPathPatterns.add("/user/message2");
        commonPathPatterns.add("/user/message3");

        //先验证服务
        //registry.addInterceptor(getClientInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        //再验证用户
        registry.addInterceptor(getJwtInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));

        super.addInterceptors(registry);
    }


    @Bean
    JwtInterceptor getJwtInterceptor(){
        return new JwtInterceptor();
    }

    @Bean
    ClientInterceptor getClientInterceptor(){
        return new ClientInterceptor();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/cache/**",
                "/api/log/save"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
