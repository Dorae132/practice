package com.nsb.practice.akkatest.immutable;

import java.util.List;

/**
 * 
 * @author Dorae
 *
 */
public class Message {

	private final int age;
	
	private final List<String> list;

	public Message(int age, List<String> list) {
		super();
		this.age = age;
		this.list = list;
	}

	public int getAge() {
		return age;
	}

	public List<String> getList() {
		return list;
	}
}
