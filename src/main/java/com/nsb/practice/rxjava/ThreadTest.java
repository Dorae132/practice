package com.nsb.practice.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.internal.schedulers.RxThreadFactory;

public class ThreadTest extends AtomicLong {
	
	public static void main(String[] args) {
		RxThreadFactory rxThreadFactory = new RxThreadFactory("nsbTest");
		ThreadTest test = new ThreadTest();
		for (int i = 0; i < 1000; i++) {
//			ExecutorService executor = Executors.newCachedThreadPool(rxThreadFactory);
//			executor.submit(new TestThread(test.getAndIncrement()));
			TestThread testThread = new TestThread(test.getAndIncrement());
			new Thread(testThread).start();
		}
	}
	
	static class TestThread extends AtomicLong implements Runnable {

		
		public TestThread(long id) {
			super();
			System.out.println(id);
		}

		@Override
		public void run() {
			while(true) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
}
