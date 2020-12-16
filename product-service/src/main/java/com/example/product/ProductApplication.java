package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class ProductApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ProductApplication.class, args);
        System.in.read();
    }

}
