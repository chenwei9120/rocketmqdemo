package com.example.order.listener;

import com.example.order.handler.TransactionListenerHandler;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName TransactionListenerExt
 * @Description TODO
 * @createTime 2020-12-08 13:51:00
 */
public class TransactionListenerExt implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object object) {
        if(object == null) {
            throw new RuntimeException("argument object can't be null.");
        }
        if(object instanceof TransactionListenerHandler) {
            return ((TransactionListenerHandler) object).execute(msg, object);
        } else {
            throw new RuntimeException("argument object must be type of TransactionListenerUtil.");
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return null;
    }
}
