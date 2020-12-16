package com.example.order;

import com.example.common.message.OrderMessage;
import com.example.common.model.TbOrder;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class IndexController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    private static final String ProducerGroup = "order-producer";

    private static final String Topic = "transTopic";

    @GetMapping("/send")
    public String sendMessage (String orderNo) {
        TbOrder order = new TbOrder();
        order.setOrderNo(orderNo);
        order.setCreateTime(new Date());
        OrderMessage orderMessage = new OrderMessage(order);
        rocketMQTemplate.sendMessageInTransaction(ProducerGroup, Topic, orderMessage, order);
        return order.getOrderNo();
    }
}
