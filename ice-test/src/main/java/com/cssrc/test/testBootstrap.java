package com.cssrc.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cssrc.test.mapper")
@EnableDiscoveryClient
@EnableEurekaClient

public class testBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(testBootstrap.class,args);
    }
}
