package com.nsb.practice.aopTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(-1)
@Aspect
@Component
public class AspectTest2 {

	@Around(value = "within(com.nsb.practice.aopTest..*) && @annotation(myAround2)")
	public void execute(ProceedingJoinPoint joinPoint, MyAround2 myAround2) throws Throwable {
		System.out.println("want to send message");
		joinPoint.proceed();
		System.out.println("sending message");
		throw new RuntimeException("error");
//		System.out.println("send message end");
	}
}