package com.nsb.practice.java.aopTest.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibInfoProxy implements MethodInterceptor {
	private Object target;

	public Object newInstance(Object source) {
		target = source;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("before method!!!");
		Object value = methodProxy.invokeSuper(o, objects);
		// 这里其实是会造成oom的问题，目的是为了在不同的对象上进行调用，这里的o是代理对象，会造成OOM的问题
//		Object value = methodProxy.invoke(o, objects);
		return value;
	}

	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\classes");
		InfoDemo instance = (InfoDemo) new CglibInfoProxy().newInstance(new InfoDemo());
		instance.welcome("zhangsan");
	}
}
