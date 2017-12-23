package com.cssrc.uc;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@RestController
public class UserCenterBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterBootstrap.class,args);
    }


    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name;
    }
}
