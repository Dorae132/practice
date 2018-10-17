package com.nsb.practice.staticclass;

public class ParentClass {

	
	public static String staticValue = "staticValue from ParentClass";

	public String value = "Value from ParentClass";
	
	// 静态绑定
	public static void staticMethod() {
		System.out.println("staticMethod from parentClass");
	}
	
	public void method() {
		System.out.println("method from parentClass");
	}
}
