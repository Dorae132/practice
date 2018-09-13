package com.nsb.practice.designpatterns.proxy.dynamicp.cglib;

import com.nsb.practice.designpatterns.proxy.dynamicp.BookFacadeImpl;

public class Main {
	public static void main(String[] args) {
		BookFacadeImpl bookFacade = new BookFacadeImpl();
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl bookCglib = (BookFacadeImpl) cglib.getInstance(bookFacade);
		bookCglib.addBook();
	}
}
