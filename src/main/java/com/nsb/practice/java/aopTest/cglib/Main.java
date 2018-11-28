package com.nsb.practice.java.aopTest.cglib;

/**
 * cglib
 * 
 * @author Dorae
 *
 */
public class Main {
	public static void main(String[] args) {
		BookFacadeImpl1 bookFacade = new BookFacadeImpl1();
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib.getInstance(bookFacade);
//		bookCglib.addBook();
		bookCglib.selfAdd();
	}
}
