package com.nsb.practice.spring.cyclicdependcy;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanB {

	@Autowired
	private BeanA beanA;
	
	public void getValue() {
		System.out.println("the beanA field of beanB: " + beanA);
	}
	
//	public BeanB(BeanA beanA) {
//        super();
//        this.beanA = beanA;
//    }

    @Override
	public String toString() {
		return "beanB";
	}
}
