package com.cssrc.warehouse;

import com.cssrc.common.MessageInfo.Message;
import com.cssrc.common.MessageInfo.StorageProcessor;
import com.cssrc.warehouse.autoconfigure.MapperScan;
import com.cssrc.warehouse.biz.StorageInfoBiz;
import com.cssrc.warehouse.entity.StorageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("com.cssrc.warehouse.mapper")
@EnableFeignClients
@EnableBinding({Processor.class, StorageProcessor.class})
public class WarehouseCenterBootstrap {

    @Autowired
    public StorageInfoBiz biz;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseCenterBootstrap.class,args);
    }

    // 监听 binding 为 OrderIntf.INPUT_ORDER 的消息
    @StreamListener(StorageProcessor.INPUT_STORAGE)
    public void inputStorage(Message message) {
        System.out.println("=====产品入库监听收到=====");
        System.out.println("产品编号：" + message.getProductId());
        System.out.println("产品名称：" + message.getProductName());
        System.out.println("产品入库数量：" + message.getQty());
        System.out.println("产品入库时间：" + message.getCreateAt());
        System.out.println("=====产品入库处理完成=====");
        StorageInfo storageInfo = new StorageInfo();
        storageInfo.setProductId(message.getProductId());
        storageInfo.setProductCode(message.getProductName());
        storageInfo.setProductName(message.getProductName());
        storageInfo.setWarehouseAmount(message.getQty());
        storageInfo.setState("入库");
        storageInfo.setWarehouseId(1);
        storageInfo.setWarehouseName("test1");
        storageInfo.setWarehouseDate(message.getCreateAt());
        storageInfo.setCrtHost("test");
        storageInfo.setCrtName("test");
        storageInfo.setCrtTime(new Date());
        storageInfo.setCrtUser("test");
        biz.insert(storageInfo);

    }
}
