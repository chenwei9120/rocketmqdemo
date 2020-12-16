package com.example.order.handler;

import com.alibaba.fastjson.JSON;
import com.example.common.model.TbOrder;
import com.example.order.mappers.TbOrderDAO;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName OrderTransactionListenerHandler
 * @Description TODO
 * @createTime 2020-12-08 14:03:00
 */
@Component
public class OrderTransactionListenerHandler extends TransactionListenerHandler {

    @Autowired
    private TbOrderDAO orderDAO;

    @Override
    public LocalTransactionState execute(Message msg, Object arg) {
        try {
            TbOrder order = JSON.parseObject(new String(msg.getBody()), TbOrder.class);
            String orderNo = order.getOrderNo();
            System.out.println("orderNo = " + orderNo);
            System.out.println("arg= " + JSON.toJSONString(super.getParameters()));
            int id = orderDAO.insert(order);
            if (id > 0) {
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
