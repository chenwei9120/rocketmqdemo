package com.example.order.handler;

import lombok.Data;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

@Data
public abstract class TransactionListenerHandler {

    private Object parameters;

    public abstract LocalTransactionState execute(Message msg, Object arg);

}
