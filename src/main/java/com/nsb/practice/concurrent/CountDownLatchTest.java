package com.nsb.practice.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Dorae
 *
 */
public class CountDownLatchTest {
	private static int num = 10;// 与会人员数量

	public static void main(String[] args) {
		VideoConference conference = new VideoConference(num);
		Thread threadConference = new Thread(conference);
		threadConference.start();// 开启await()方法，在内部计数器为0之前线程处于等待状态
		for (int i = 0; i < num; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			Thread t = new Thread(p);
			t.start();
		}
	}
}

// 视频会议类
class VideoConference implements Runnable {
	private final CountDownLatch controller;

	public VideoConference(int number) {
		// 计数器内等待其他线程的初始化数目
		controller = new CountDownLatch(number);
	}

	public void arrive(String name) {
		System.out.printf("%s has arrived.\n", name);
		controller.countDown();// 调用countDown()方法，使内部计数器减1
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

	@Override
	public void run() {
		synchronized (VideoConference.class) {
			if (controller.getCount() != 0) {
				System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
			}
		}
		try {
			controller.await();// 等待，直到CoutDownLatch计数器为0

			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// 参加会议的人员类
class Participant implements Runnable {

	private VideoConference conference;
	private String name;

	public Participant(VideoConference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	@Override
	public void run() {
		Long duration = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		conference.arrive(name);// 每到一个人员，CountDownLatch计数器就减少1
	}
}
