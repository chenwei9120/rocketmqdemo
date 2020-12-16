package com.example.common.message;

import com.example.common.model.TbOrder;
import org.springframework.messaging.support.GenericMessage;

public class OrderMessage extends GenericMessage<TbOrder> {

    public OrderMessage(TbOrder order) {
        super(order);
    }
}
