package com.nsb.practice.java.aopTest.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * cglib
 * 
 * @author Dorae
 *
 */
public class Main {
	public static void main(String[] args) {
//	    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\Dorae\\Desktop\\debug class");
		BookFacadeImpl1 bookFacade = new BookFacadeImpl1();
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib.getInstance(bookFacade);
//		bookCglib.addBook();
		System.err.println("bookfacade: " + bookFacade.hashCode());
		System.err.println("bookCglib: " + bookCglib.hashCode());
		bookCglib.selfAdd();
	}
}
