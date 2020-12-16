package com.example.product.service.impl;

import com.example.common.model.TbProduct;
import com.example.common.service.ProductService;
import com.example.product.mappers.TbProductDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private TbProductDAO productDAO;

    @Override
    public void deduct(Integer id, Integer count) {
        System.out.println("deduct method");
        TbProduct productOld = productDAO.selectByPrimaryKey(id);
        TbProduct product = new TbProduct();
        product.setId(id);
        product.setCount(productOld.getCount() - count);
        int i = 1;
        i = i / --i;
        productDAO.updateByPrimaryKeySelective(product);
    }
}
