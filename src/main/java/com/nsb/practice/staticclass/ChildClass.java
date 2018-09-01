package com.nsb.practice.staticclass;

public class ChildClass extends TestClass {

	// 静态绑定
	public static String staticValueFromTestClass = "staticValue from ChildClass";
	
	public static String staticValue = "staticValuei From ChildClass";
	
	public static void main(String[] args) {
		TestInterface childClass = new ChildClass();
		System.out.println(childClass.staticValue);
	}
}
