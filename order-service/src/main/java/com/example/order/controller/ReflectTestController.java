package com.example.order.controller;

import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName ReflectTestControll
 * @Description TODO
 * @createTime 2020-12-08 17:25:00
 */
@RestController
public class ReflectTestController {

    @GetMapping("/test")
    public String test() throws NoSuchFieldException {
        Message message = new Message();
        Field field = message.getClass().getDeclaredField("properties");
        field.setAccessible(true);
        return "";
    }
}
