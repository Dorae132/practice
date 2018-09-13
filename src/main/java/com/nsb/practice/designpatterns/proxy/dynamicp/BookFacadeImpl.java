package com.nsb.practice.designpatterns.proxy.dynamicp;

public class BookFacadeImpl implements BookFacade {

	@Override
	public void addBook() {
		System.out.println("增加图书的方法...");
	}

	@Override
	public void deleteBook() {
		System.out.println("delete图书的方法...");
	}

}
