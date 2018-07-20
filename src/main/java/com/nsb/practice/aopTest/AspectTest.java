package com.nsb.practice.aopTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Dorae
 *
 */
@Order(0)
@Aspect
@Component
public class AspectTest {

	@Around(value = "within(com.nsb.practice.aopTest..*) && @annotation(myAround)")
	public void execute(ProceedingJoinPoint joinPoint, MyAround myAround) throws Throwable {
		System.out.println("transaction start");
		joinPoint.proceed();
		System.out.println("transaction end");
	}
}