package com.cssrc.uc;


import com.cssrc.uc.autoconfigure.MapperScan;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("com.cssrc.uc.mapper")
@EnableFeignClients
//@EnableBinding({Processor.class, OrderProcessor.class, ProductProcessor.class})
@RestController
@RequestMapping("user")
public class UserCenterBootstrap {

    //@Autowired
    //@Qualifier("output")
    //MessageChannel output;

    //@Autowired
    //@Qualifier("outputOrder")
    //MessageChannel outputOrder;

    //@Autowired
    //ProductProcessor productProcessor;


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

    // 监听 binding 为 Processor.INPUT 的消息
    //@StreamListener(Processor.INPUT)
    //public void input(Message<String> message) {
    //    System.out.println("一般监听收到：" + message.getPayload());
    //}

    // 监听 binding 为 OrderIntf.INPUT_ORDER 的消息
    //@StreamListener(OrderProcessor.INPUT_ORDER)
    //public void inputOrder(Order order) {
    //    System.out.println("=====订单监听收到=====");
    //    System.out.println("订单编号：" + order.getOrderNum());
    //    System.out.println("订单类型：" + order.getType());
    //    System.out.println("订单数量：" + order.getNum());
    //    System.out.println("=====订单处理完成=====");
    //}


    //@StreamListener(ProductProcessor.INPUT_PRODUCT_ADD)
    //public void inputProductAdd(Message<String> message) {
    //    System.out.println("新增产品监听收到：" + message.getPayload());
    //}




    // 定时轮询发送消息到 binding 为 Processor.OUTPUT
    //@Bean
    //@InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "3000", maxMessagesPerPoll = "1"))
    //public MessageSource<String> timerMessageSource() {

    //    System.out.println("简单发送");
    //    return () -> MessageBuilder.withPayload("短消息-" + new Date()).build();
    //}

    //@RequestMapping("/message1")
    //public String message1() {
    //    // 字符串类型发送MQ
    //    System.out.println("字符串信息发送");
    //    output.send(MessageBuilder.withPayload("大家好").build());
    //    return "send message1";
    //}

    //@RequestMapping("/message2")
    //public String message2() {
    //    // 使用 定义的接口的方式来发送
     //   System.out.println("新增产品发送");
    //    productProcessor.outputProductAdd().send(MessageBuilder.withPayload("添加一个产品").build());
    //    return "send message2";
    //}

    //@RequestMapping("/message3")
    //public String message3() {
    //    // 实体类型发送MQ
    //    System.out.println("订单实体发送");
    //    Order appleOrder = new Order();
    //    appleOrder.setOrderNum("0000001");
    //    appleOrder.setNum(10);
    //    appleOrder.setType("APPLE");
    //    appleOrder.setCreateAt(new Date());
    //    // 使用 注入 MessageChannel 方式发送
    //    outputOrder.send(MessageBuilder.withPayload(appleOrder).build());
    //    return "send message3";
    // }


}






