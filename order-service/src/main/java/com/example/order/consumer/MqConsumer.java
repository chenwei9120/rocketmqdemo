package com.example.order.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName MqConsumer
 * @Description TODO
 * @createTime 2020-12-08 14:58:00
 */
@Component
public class MqConsumer {

    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("batch_group");

    @PostConstruct
    public void initConsumer() throws MQClientException {
        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.subscribe("order-settle-topic", "tag1||tag2");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(msg.getTags());
                    System.out.println("queueId=" + msg.getQueueId() + "," + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }

    @PreDestroy
    public void destroy () {
        consumer.shutdown();
    }
}
