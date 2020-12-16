package com.example.product.listen;

import com.example.common.model.TbOrder;
import com.example.product.mappers.TbProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RocketMQMessageListener(topic = "TestMessage",
        consumeMode = ConsumeMode.ORDERLY, consumerGroup = "product-consumer")//${rocketmq.consumer.group}
public class RocketMQConsumerListener implements RocketMQListener<String> {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired(required = false)
    private TbProductDAO productDAO;

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void onMessage(TbOrder order) {
//
//        RLock lock = redissonClient.getLock(order.getOrderNo());
//        if (lock.tryLock()) {
//            log.info("lock.tryLock() success");
//            productDAO.deduct(1L);
//        } else {
//            log.info("lock.tryLock() failure");
//        }
//        log.info("接收到消息：" + order.getOrderNo());
//    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }
}
