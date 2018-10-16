package com.nsb.practice.cyclicdependcy;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanB {

	@Autowired
	private BeanA beanA;
	
	public void getValue() {
		System.out.println("the field of beanB: " + beanA);
	}
	
	@Override
	public String toString() {
		return "beanB";
	}
}
