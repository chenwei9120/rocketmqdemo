package com.example.apidemo.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactory implements BeanFactoryAware {

    private static BeanFactory factory = null;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        factory = beanFactory;
    }

    public static BeanFactory getBeanFactory() {
        return factory;
    }
}
