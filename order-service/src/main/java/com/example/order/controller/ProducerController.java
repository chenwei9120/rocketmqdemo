package com.example.order.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.model.TbOrder;
import com.example.order.handler.OrderTransactionListenerHandler;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName ProducerController
 * @Description TODO
 * @createTime 2020-12-07 16:41:00
 */
@RestController
@RequestMapping("producer")
public class ProducerController {

    private TransactionMQProducer producer = new TransactionMQProducer("producer_group");

    private static final String Topic = "order-settle-topic";

    @Autowired
    private  TransactionListener transactionListener;

    @PostConstruct
    public void init() {
        try {
            producer.setNamesrvAddr("127.0.0.1:9876");
            ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                    100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
            producer.setExecutorService(executorService);
            producer.setTransactionListener(transactionListener);
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

//    @RequestMapping("/create")
//    public void createOrder(String orderNo)  {
//        try {
//            TbOrder order = new TbOrder();
//            order.setOrderNo(orderNo);
//            Map<String, Object> headMap = new HashMap<>();
//            headMap.put("createTime", System.currentTimeMillis());
//
//            Message msg = new Message(Topic, "", "KEY",
//                    JSON.toJSONString(order).getBytes(RemotingHelper.DEFAULT_CHARSET));
//            SendResult sendResult = producer.sendMessageInTransaction(msg, headMap);
//            System.out.printf("%s%n", sendResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Autowired
    OrderTransactionListenerHandler handler;

    @RequestMapping("/create")
    public void createOrder(String orderNo)  {
        try {
            TbOrder order = new TbOrder();
            order.setOrderNo(orderNo);
            Map<String, Object> headMap = new HashMap<>();
            headMap.put("createTime", System.currentTimeMillis());

            Message msg = new Message(Topic, "tag1", "KEY",
                    JSON.toJSONString(order).getBytes(RemotingHelper.DEFAULT_CHARSET));
//            OrderTransactionListenerHandler handler = new OrderTransactionListenerHandler();
            handler.setParameters(headMap);
            SendResult sendResult = producer.sendMessageInTransaction(msg, handler);
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/delete")
    public void deleteOrder()  {
        try {
            Message msg = new Message(Topic, "", "KEY",
                    "deleteOrder".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.sendMessageInTransaction(msg, null);
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        producer.shutdown();
    }
}
