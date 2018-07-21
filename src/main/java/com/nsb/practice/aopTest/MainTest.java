package com.nsb.practice.aopTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("test")
public class MainTest implements Test {
	
	@MyAround
	@MyAround2
	public void biz() {
		System.out.println("-------------------biz-----------------------");
		throw new RuntimeException("error");
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
		Test test = context.getBean("test", Test.class);
		test.biz();
	}
}

interface Test {
	void biz();
}