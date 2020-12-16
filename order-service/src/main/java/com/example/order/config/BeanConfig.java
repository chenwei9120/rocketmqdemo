package com.example.order.config;

import com.example.order.listener.TransactionListenerExt;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenwei
 * @version 1.0.0
 * @ClassName BeanConfig
 * @Description TODO
 * @createTime 2020-12-08 13:23:00
 */
@Configuration
public class BeanConfig {

    @Bean
    public TransactionListener transactionListener() {
        return new TransactionListenerExt();
    }
}
