package com.example.order.service.impl;

import com.example.common.model.TbOrder;
import com.example.common.service.OrderService;
import com.example.order.mappers.TbOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private TbOrderDAO orderDAO;

    @Override
    public int createOrder(TbOrder order) {
        return orderDAO.insert(order);
    }

    @Override
    public TbOrder selectOrderByOrderSn(String orderSn) {
        return orderDAO.selectOrderByOrderSn(orderSn);
    }
}
