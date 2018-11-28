package com.nsb.practice.java.aopTest.jdkproxy;

public class BookFacadeImpl implements BookFacade {

	@Override
	public void addBook() {
		System.out.println("add book");
		deleteBook();
	}

	@Override
	public void deleteBook() {
		System.out.println("delete book");
	}

}
