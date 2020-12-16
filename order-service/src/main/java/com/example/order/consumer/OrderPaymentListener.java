package com.example.order.consumer;

import com.example.common.model.TbOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "transTopic", consumerGroup = "order-group")
public class OrderPaymentListener implements RocketMQListener<TbOrder> {

    @Override
    public void onMessage(TbOrder order) {
        System.out.printf("------- message received: %s \n", "------orderNo=" + order.getOrderNo());
    }
}
