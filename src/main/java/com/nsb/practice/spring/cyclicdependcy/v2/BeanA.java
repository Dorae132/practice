package com.nsb.practice.spring.cyclicdependcy.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Order(1)
//@Component
public class BeanA {

    @Autowired
    private BeanB beanB;
    
    @Async
    public void methodA() {
        
    }
}
