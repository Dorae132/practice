package com.nsb.practice.spring.cyclicdependcy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	private static int classValue = 0;
	
	private int value = 0;
	
	public Main() {
		super();
		this.value = classValue;
		classValue ++;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
//		System.out.println(context.getBean("com.nsb.practice.Test#3"));
//		Enhancer enhancer = new Enhancer();
//		enhancer.setSuperclass(BeanB.class);
//		enhancer.setCallback(new MethodInterceptor() {
//
//			@Override
//			public Object intercept(Object paramObject, Method paramMethod, Object[] paramArrayOfObject,
//					MethodProxy paramMethodProxy) throws Throwable {
//				System.out.println("before beanB run...");
//				Object invokeSuper = paramMethodProxy.invokeSuper(paramObject, paramArrayOfObject);
//				System.out.println("after beanB run...");
//				return invokeSuper;
//			}
//		});
//		BeanB create = (BeanB) enhancer.create();
		BeanA beanA = (BeanA) context.getBean("beanA");
		BeanB beanB = (BeanB) context.getBean("beanB");
		beanA.getValue();
		beanB.getValue();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "testBean#" + this.value;
	}
	
}
