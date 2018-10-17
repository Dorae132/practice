package com.nsb.practice.staticclass;

public interface ParentInterface {

	// 静态绑定
	public static String staticValue = "staticValue from ParentInterface";

	public String value = "Value from ParentInterface";
	
	static void interfaceMethod() {
		System.out.println("interfaceMethod from parentInterface");
	}
}
