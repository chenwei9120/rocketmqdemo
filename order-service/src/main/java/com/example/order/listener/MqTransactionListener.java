package com.example.order.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @ClassName MqTransactionListener
 * @Description TODO
 * @Author chenwei
 * @Date 2020/10/9 12:24
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "order-producer2")
public class MqTransactionListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("txProducerGroup = \"order-producer2\"");
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("txProducerGroup = \"order-producer2\"");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
