package com.example.order.model;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public interface LocalTransactionDo {

    void execute(Message msg, Object arg);

    void check(MessageExt msg);
}
