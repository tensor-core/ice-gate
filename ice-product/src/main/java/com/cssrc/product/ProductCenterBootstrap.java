package com.cssrc.product;

import com.cssrc.common.MessageInfo.StorageProcessor;
import com.cssrc.product.autoconfigure.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("com.cssrc.product.mapper")
@EnableFeignClients
@EnableBinding({StorageProcessor.class})
public class ProductCenterBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ProductCenterBootstrap.class,args);
    }
}
