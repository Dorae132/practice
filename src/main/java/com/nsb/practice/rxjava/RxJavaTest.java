package com.nsb.practice.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 参考
 * {@link https://www.jianshu.com/p/88aacbed8aa5}
 * @author Dorae
 *
 */
public class RxJavaTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + "start");
//		Observable.create(new ObservableOnSubscribe<Integer>() {
//			@Override
//			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//				System.out.println("Observable thread is : " + Thread.currentThread().getName());
////				TimeUnit.SECONDS.sleep(3);
//				e.onNext(1);
//				e.onComplete();
//			}
//		})
////				.subscribeOn(Schedulers.newThread())
//				// .subscribeOn(Schedulers.io())
////				.observeOn(Schedulers.newThread())
//				.doOnNext(new Consumer<Integer>() {
//					@Override
//					public void accept(@NonNull Integer integer) throws Exception {
//						System.out.println(
//								"After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
//					}
//				})
//				// .observeOn(Schedulers.io())
//				.subscribe(new Consumer<Integer>() {
//					@Override
//					public void accept(@NonNull Integer integer) throws Exception {
//						System.out.println("After observeOn(io)，Current thread is " + Thread.currentThread().getName());
//					}
//				});
		Scheduler newThread = Schedulers.newThread();
		for (int i = 0; i < 1000; i++) {
		Observable.create(new ObservableOnSubscribe<String>() {

			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				System.out.println("事件源线程start：" + Thread.currentThread().getName());
				System.out.println("事件源 subscribe start");
				emitter.onNext("nsb");
//				TimeUnit.SECONDS.sleep(3);
				System.out.println("事件源 subscribe end");
				System.out.println("事件源线程end：" + Thread.currentThread().getName());
			}
		})
//		.subscribeOn(newThread)
//		.subscribeOn(newThread)
		.filter(e -> e.equals("nsb"))
//		.map(e -> "MAP2: " + e)
//		.subscribeOn(newThread)
//		.subscribeOn(newThread)
		.map(e -> "MAP3: " + e)
		.observeOn(newThread)
//		.observeOn(newThread)
		.subscribe(new Observer<String>() {

			@Override
			public void onNext(@NonNull String o) {
				while (true) {
					System.out.println("#观察者线程：" + Thread.currentThread().getName());
				}
//				try {
//					TimeUnit.SECONDS.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("观察者 onNext: " + o);
			}

			@Override
			public void onSubscribe(Disposable d) {
				System.out.println("观察者onSubscribe: " + d + "### " + Thread.currentThread().getName());
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}
		});
		}
		System.out.println("main end");
		TimeUnit.SECONDS.sleep(100000);
	}
}
