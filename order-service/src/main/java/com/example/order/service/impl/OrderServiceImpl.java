package com.example.order.service.impl;

import com.example.common.model.TbOrder;
import com.example.common.service.OrderService;
import com.example.order.mappers.TbOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private TbOrderDAO orderDAO;

    @Override
    public int createOrder() {
        System.out.println("create order.");
        TbOrder order = new TbOrder();
        order.setCreateTime(new Date());
//        int i = 1;
//        i = i / (i - 1);
        return orderDAO.insert(order);
    }
}
