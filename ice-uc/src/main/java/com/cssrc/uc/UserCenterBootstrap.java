package com.cssrc.uc;


import com.cssrc.uc.autoconfigure.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("com.cssrc.uc.mapper")
@EnableFeignClients
//@RestController
//@RequestMapping("user")
public class UserCenterBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterBootstrap.class,args);
    }

    //获取配置文件参数
    //@Value("${foo}")
    //private String foostr;

    //@RequestMapping("/test")
    //public String home(@RequestParam String name) {
    //    return "hi "+name+" foo "+foostr;
    //}

    //@RequestMapping("/hi")
    //public String hi() {
    //    return "hi test";
    //}

}
