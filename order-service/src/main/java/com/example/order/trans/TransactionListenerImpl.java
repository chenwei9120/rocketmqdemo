package com.example.order.trans;

import com.example.common.message.OrderMessage;
import com.example.common.model.TbOrder;
import com.example.common.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ：
 * @date：2020/08/31
 * @version: V1.0
 * @slogan:
 * @description :
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "order-producer")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {


    @Resource
    private OrderService orderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            TbOrder order = ((TbOrder) arg);
            int id = orderService.createOrder(order);
            if (id > 0) {
                return RocketMQLocalTransactionState.COMMIT;
            } else {
                return RocketMQLocalTransactionState.ROLLBACK;
            }
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }


    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        OrderMessage orderMessage = (OrderMessage) msg;
        TbOrder order = orderMessage.getPayload();
        TbOrder tbOrder = orderService.selectOrderByOrderSn(order.getOrderNo());
        if (tbOrder != null) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}