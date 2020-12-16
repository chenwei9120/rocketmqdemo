package com.example.order.listener;

import com.alibaba.fastjson.JSON;
import com.example.common.message.OrderMessage;
import com.example.common.model.TbOrder;
import com.example.order.first.OrderMessagePayload;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName OriginListener
 * @Description TODO
 * @createTime 2020-12-09 11:28:00
 */
@RocketMQTransactionListener(txProducerGroup = "send-order-message")
public class OriginListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        OrderMessagePayload order = JSON.parseObject(new String((byte[]) msg.getPayload()), OrderMessagePayload.class);
        if (order != null) {
            System.out.println(JSON.toJSONString(order));
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
