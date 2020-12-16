package com.example.order.listener;

import com.alibaba.fastjson.JSON;
import com.example.common.model.TbOrder;
import com.example.order.mappers.TbOrderDAO;
import com.example.common.message.OrderMessage;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

/**
 * @author ：
 * @date：2020/08/31
 * @version: V1.0
 * @slogan:
 * @description :
 */
@RocketMQTransactionListener(txProducerGroup = "order-producer")
public class SpringTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired(required = false)
    private TbOrderDAO orderDAO;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            OrderMessage orderMessage = ((OrderMessage) arg);
            String orderNo = orderMessage.getOrder().getOrderNo();
            System.out.println("orderNo = " + orderNo);
            System.out.println("arg= " + arg.toString());
            TbOrder order = new TbOrder();
            Long createTime = (Long) orderMessage.getHeaders().get("createTime");
            order.setCreateTime(new Date(createTime));
            order.setOrderNo(orderNo);
            orderDAO.insert(order);
            if (order.getId() > 0) {
                //return RocketMQLocalTransactionState.COMMIT;
                return RocketMQLocalTransactionState.UNKNOWN;
            } else {
                //return RocketMQLocalTransactionState.ROLLBACK;
                return RocketMQLocalTransactionState.UNKNOWN;
            }
        } catch (Exception e) {
            //return RocketMQLocalTransactionState.ROLLBACK;
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }


    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        TbOrder orderOld = JSON.parseObject(new String((byte[]) msg.getPayload()), TbOrder.class);
        if(orderOld != null) {
            Integer id = orderOld.getId();
            TbOrder order = orderDAO.selectByPrimaryKey(id);
            if (order != null) {
                return RocketMQLocalTransactionState.COMMIT;
            } else {
                return RocketMQLocalTransactionState.UNKNOWN;
            }
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}