package com.example.order.first;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName OrderMessage
 * @Description TODO
 * @createTime 2020-12-09 11:09:00
 */
public class OrderMessageOrigin extends GenericMessage<OrderMessagePayload> {

    private OrderMessagePayload order;

    private MessageHeaders headers;

    public OrderMessageOrigin() {
        super(null);
    }

    public OrderMessageOrigin(OrderMessagePayload payload) {
        super(payload);
    }

    public OrderMessageOrigin(OrderMessagePayload payload, Map<String, Object> headers) {
        super(payload, headers);
        this.order = (OrderMessagePayload)payload;
        this.headers = new MessageHeaders(headers);
    }

    public OrderMessagePayload getOrder() {
        return order;
    }

    public void setOrder(OrderMessagePayload order) {
        this.order = order;
    }

    @Override
    public MessageHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MessageHeaders headers) {
        this.headers = headers;
    }

    public OrderMessageOrigin(OrderMessagePayload payload, MessageHeaders headers) {
        super(payload, headers);
        this.order = (OrderMessagePayload)payload;
        this.headers = headers;
    }
}
