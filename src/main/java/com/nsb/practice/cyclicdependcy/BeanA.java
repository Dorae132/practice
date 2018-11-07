package com.nsb.practice.cyclicdependcy;

public class BeanA {

//	@Autowired
	private BeanB beanB;

	public void getValue() {
		System.out.println("the field od beanA: " + beanB);
	}

	public BeanA(BeanB beanB) {
		super();
		this.beanB = beanB;
	}

	@Override
	public String toString() {
		return "beanA";
	}
	
}
