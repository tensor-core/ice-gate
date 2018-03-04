package com.cssrc.auth;

import com.cssrc.auth.autoconfigure.MapperScan;
import com.cssrc.common.blockchain.ExecuteCommands;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.cssrc.auth.mapper")
@ComponentScan("com.cssrc.auth")
@EnableFeignClients
public class AuthServerBootstrap {
    public static void main(String[] args) {

        SpringApplication.run(AuthServerBootstrap.class,args);
    }
}
