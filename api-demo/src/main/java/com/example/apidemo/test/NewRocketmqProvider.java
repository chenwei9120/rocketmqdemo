package com.example.apidemo.test;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewRocketmqProvider {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public boolean send(String topic, String msg){
        rocketMQTemplate.convertAndSend(topic, msg);
        return true;
    }
}