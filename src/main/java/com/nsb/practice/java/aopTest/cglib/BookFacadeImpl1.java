package com.nsb.practice.java.aopTest.cglib;

public class BookFacadeImpl1 {
	public void addBook() {
		System.out.println("新增图书...");
	}
	
	public void selfAdd() {
	    System.out.println("selfADD");
		this.addBook();
	}
}
