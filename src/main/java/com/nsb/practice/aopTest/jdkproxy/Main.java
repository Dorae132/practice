package com.nsb.practice.aopTest.jdkproxy;

public class Main {
	public static void main(String[] args) {
		BookFacadeImpl bookFacadeImpl = new BookFacadeImpl();
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
		bookfacade.addBook();
		bookfacade.deleteBook();
	}
}
