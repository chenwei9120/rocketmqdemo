package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@ConditionalOnProperty(prefix = "rocketmq", value = "name-server")
@ImportResource({"classpath:provider.xml"})
public class OrderServiceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OrderServiceApplication.class, args);
        System.in.read();
    }

}
