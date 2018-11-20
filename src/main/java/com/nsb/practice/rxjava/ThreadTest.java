package com.nsb.practice.rxjava;

import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ThreadTest {
	
	private static void logThread(Object obj, Thread thread){
	    System.out.println("onNext:"+obj+" -"+Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		
		ObservableOnSubscribe onSub = new ObservableOnSubscribe<Integer>() {

			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				System.out.println("OnSubscribe -"+Thread.currentThread().getName());
				emitter.onNext(1);
				emitter.onComplete();
			}
		};
//		System.out.println("--------------①-------------");
//		Observable.create(onSub)
//		        .subscribe(integer->logThread(integer, Thread.currentThread()));
		System.out.println("--------------②-------------");
		Observable.create(onSub)
		        .subscribeOn(Schedulers.io())
		        .subscribe(integer->logThread(integer, Thread.currentThread()));
//		System.out.println("--------------③-------------");
//		Observable.create(onSub)
//		        .subscribeOn(Schedulers.newThread())
//		        .subscribe(integer->logThread(integer, Thread.currentThread()));
//		System.out.println("--------------④-------------");
//		Observable.create(onSub)
//		        .subscribeOn(Schedulers.newThread())
//		        .observeOn(Schedulers.newThread())
//		        .subscribe(integer->logThread(integer, Thread.currentThread()));
//		System.out.println("--------------⑤-------------");
//		Observable.create(onSub)
//		        .subscribeOn(Schedulers.newThread())
//		        .observeOn(Schedulers.newThread())
//		        .subscribe(integer->logThread(integer, Thread.currentThread()));
//		System.out.println("--------------⑥-------------");
//		Observable.interval(100, TimeUnit.MILLISECONDS)
//		        .take(1)
//		        .subscribe(integer->logThread(integer, Thread.currentThread()));
	}
}
