package com.nsb.practice.innerclass;

/**
 * 内部类
 * @author Dorae
 *
 */
public class InnerClass {

	private String outStr;
	
	class InnerClassA {
		public String getStr() {
			return outStr;
		}
	}
}
