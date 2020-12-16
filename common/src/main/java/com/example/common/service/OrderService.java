package com.example.common.service;

import com.example.common.model.TbOrder;

public interface OrderService {

    int createOrder(TbOrder order);

    TbOrder selectOrderByOrderSn(String orderSn);
}
