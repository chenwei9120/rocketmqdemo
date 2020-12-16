package com.example.product.mappers;

import com.example.common.model.TbProduct;
import org.springframework.stereotype.Repository;

/**
 * TbProductDAO继承基类
 */
@Repository
public interface TbProductDAO extends MyBatisBaseDao<TbProduct, Integer> {
    void deduct(Long id);
}