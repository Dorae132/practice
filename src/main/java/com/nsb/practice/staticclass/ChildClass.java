package com.nsb.practice.staticclass;

public class ChildClass extends ParentClass implements ParentInterface {

	// 静态绑定
	public static String staticValue = "staticValue from ChildClass";
	
	public String value = "value from ChildClass";
	
	public static void main(String[] args) {
		ChildClass childClass = new ChildClass();
		System.out.println(childClass.value);
		childClass.method();
		childClass.staticMethod();
//		childClass.
	}
	
//	public static void staticMethod() {
//		System.out.println("staticMethod from childClass");
//	}
//	
//	public void method() {
//		System.out.println("method from childClass");
//	}
	
//	static void interfaceMethod() {
//		System.out.println("interfaceMethod from parentInterface");
//	}
}
