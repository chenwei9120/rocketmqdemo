package com.example.order.listener;

import com.alibaba.fastjson.JSON;
import com.example.common.model.TbOrder;
import com.example.order.mappers.TbOrderDAO;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName TransactionListenerImpl2
 * @Description TODO
 * @createTime 2020-12-07 16:51:00
 */
@Component
public class RocketMqTransactionListener {//implements TransactionListener {

    @Autowired
    private TbOrderDAO orderDAO;

    @PostConstruct
    public void init() {
        System.out.println("TransactionListenerImpl2::init");
    }

//    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            TbOrder order = JSON.parseObject(new String(msg.getBody()), TbOrder.class);
            String orderNo = order.getOrderNo();
            System.out.println("orderNo = " + orderNo);
            System.out.println("arg= " + arg.toString());
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

//    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        TbOrder order = JSON.parseObject(new String(msg.getBody()), TbOrder.class);
        //Integer id = order.getId();
        //order = orderDAO.
        //需要按订单编号查询
        if (order != null) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
