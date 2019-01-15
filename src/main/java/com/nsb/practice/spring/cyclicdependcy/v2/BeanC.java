package com.nsb.practice.spring.cyclicdependcy.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
public class BeanC {

    @Autowired
    private BeanA beanA;
}
