package com.nsb.practice.aopTest.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib
 * @author Dorae
 *
 */
public class Main {
	public Main() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Main.class);
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("before method run");
				Object invokeSuper = proxy.invokeSuper(obj, args);
				System.out.println("after method run");
				return invokeSuper;
			}
		});
		Main main = (Main) enhancer.create();
	}
}
