package com.nsb.practice.cyclicdependcy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class BeanInitA implements InitializingBean {

	@Autowired
	private BeanInitB beanInitB;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after properties set");
	}

	public void initMethod() {
		System.out.println("init method");
	}
}
