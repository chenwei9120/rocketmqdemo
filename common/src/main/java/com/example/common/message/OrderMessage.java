package com.example.common.message;

import com.example.common.model.TbOrder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

public class OrderMessage extends GenericMessage<TbOrder> {

    private TbOrder order;

    private MessageHeaders headers;

    public OrderMessage(TbOrder payload) {
        super(payload);
    }

    public OrderMessage(TbOrder payload, Map<String, Object> headers) {
        super(payload, headers);
        this.order = (TbOrder)payload;
        this.headers = new MessageHeaders(headers);
    }

    public OrderMessage(TbOrder payload, MessageHeaders headers) {
        super(payload, headers);
        this.order = (TbOrder)payload;
        this.headers = headers;
    }

    public TbOrder getOrder() {
        return order;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }

    @Override
    public MessageHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MessageHeaders headers) {
        this.headers = headers;
    }
}
