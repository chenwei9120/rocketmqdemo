package com.example.apidemo.controller;

import com.alibaba.excel.EasyExcel;
import com.example.apidemo.util.MyBeanFactory;
import com.example.common.service.OrderService;
import com.example.common.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(Integer productId) {
        OrderService orderService = MyBeanFactory.getBeanFactory().getBean(OrderService.class);
        orderService.createOrder();

        ProductService productService = MyBeanFactory.getBeanFactory().getBean(ProductService.class);
        productService.deduct(productId, 1);

        return "OK";
    }

    @GetMapping("/export")
    public void index(HttpServletResponse response) throws IOException {
        List<List<String>> table = new ArrayList<List<String>>();
        List<String> list1 = new ArrayList<String>();
        list1.add("a11");
        list1.add("a12");
        table.add(list1);
        List<String> list2 = new ArrayList<String>();
        list2.add("a21");
        list2.add("a22");
        table.add(list2);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String  fileName = URLEncoder.encode("hello.xls", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream()).sheet("充电订单").doWrite(table);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
