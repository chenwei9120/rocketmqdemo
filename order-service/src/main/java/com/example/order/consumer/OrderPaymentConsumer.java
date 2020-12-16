package com.example.order.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@RocketMQMessageListener(topic = "order-settle-topic", consumerGroup = "transTopic-group")
public class OrderPaymentConsumer {//} implements RocketMQListener<String> {

    //@Override
    public void onMessage(String message) {
        System.out.printf("------- message received: %s \n", message);
    }
}
