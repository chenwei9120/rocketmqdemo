package com.example.order;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@ConditionalOnProperty(prefix = "rocketmq", value = "name-server")
public class OrderServiceApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OrderServiceApplication.class, args);
        System.in.read();
    }

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) throws Exception {

//        Message msg = MessageBuilder.withPayload("Hello RocketMQ ").
//                setHeader(RocketMQHeaders.KEYS, "123456").build();
//        //rocketMQTemplate.syncSend("trans", message, 60000);
//        SendResult sendResult = rocketMQTemplate.sendMessageInTransaction("order-producer",
//                "trans", msg, null);

    }

}
