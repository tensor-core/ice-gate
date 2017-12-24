package com.cssrc.uc;


import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("user")
public class UserCenterBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterBootstrap.class,args);
    }

    //获取配置文件参数
    @Value("${foo}")
    private String foostr;

    @RequestMapping("/test")
    public String home(@RequestParam String name) {
        return "hi "+name+" foo "+foostr;
    }

    @RequestMapping("/hi")
    public String hi() {
        return "hi test";
    }

}
