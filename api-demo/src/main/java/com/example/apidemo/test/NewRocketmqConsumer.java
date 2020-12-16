package com.example.apidemo.test;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "rockettest", consumerGroup = "myconsumer")
public class NewRocketmqConsumer implements RocketMQListener<String> {

    public void onMessage(String message) {
        System.out.println("收到消息：" + message);
    }

}