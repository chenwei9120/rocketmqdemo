package com.example.order.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.message.OrderMessage;
import com.example.common.model.TbOrder;
import com.example.order.consumer.MqConsumer;
import com.example.order.first.OrderMessageOrigin;
import com.example.order.first.OrderMessagePayload;
import com.example.order.handler.OrderListenerHandler;
import com.example.order.handler.OrderTransactionListenerHandler;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class OrderController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private static final String ProducerGroup = "order-producer";

    private static final String Topic = "order-settle-topic";

    @GetMapping("/send/message")
    public String sendMessage(String orderNo) {
//        Message msg = MessageBuilder.withPayload(orderNo).
//                setHeader("createTime", System.currentTimeMillis()).build();
        TbOrder order = new TbOrder();
        order.setOrderNo(orderNo);
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("createTime", System.currentTimeMillis());
        OrderMessage orderMessage = new OrderMessage(order, headMap);
        //rocketMQTemplate.syncSend("trans", message, 60000);
        rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                Long id = (Long) arg;  //根据订单id选择发送queue
                long index = id % mqs.size();
                return mqs.get((int) index);
            }
        });
        SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(ProducerGroup,
                Topic, orderMessage, orderMessage);
        return sendResult.getMsgId();
    }

    @GetMapping("/first")
    public String sendMessage2() {
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("myCreateTime", System.currentTimeMillis());
        headMap.put("handler", OrderTransactionListenerHandler.class.getCanonicalName());
        headMap.put("consumer", OrderListenerHandler.class.getCanonicalName());
        OrderMessagePayload payload = new OrderMessagePayload();
        payload.setAccountId(12345L);
        payload.setOrderSn("DDDDD");
        payload.setOrderMoney(new BigDecimal("12.345"));
        payload.setOrderId(1111L);
        OrderMessageOrigin orderMessage = new OrderMessageOrigin(payload, headMap);
        rocketMQTemplate.sendMessageInTransaction(
                "send-order-message", "order-test", orderMessage, new Object());
        return "";
    }
}
