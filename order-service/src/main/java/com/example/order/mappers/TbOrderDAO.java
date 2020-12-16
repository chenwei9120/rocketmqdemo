package com.example.order.mappers;

import com.example.common.model.TbOrder;
import org.springframework.stereotype.Repository;

/**
 * TbOrderDAO继承基类
 */
@Repository
public interface TbOrderDAO extends MyBatisBaseDao<TbOrder, Integer> {

    TbOrder selectOrderByOrderSn(String orderSn);
}