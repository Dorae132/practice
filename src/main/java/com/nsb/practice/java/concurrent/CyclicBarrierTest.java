package com.nsb.practice.java.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @author Dorae
 *
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
			@Override
			public void run() {
				System.out.println("所有玩家进入第 2 关！");
			}
		});
		for (int i = 1; i <= 4; i++) {
			new Thread(new Player(i, cyclicBarrier)).start();
		}
		System.out.println("end");
	}
}

/**
 * 玩家类
 *
 * @author Dorae
 */
class Player implements Runnable {
	private CyclicBarrier cyclicBarrier;
	private int id;

	public Player(int id, CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println("玩家" + id + "正在玩第 1 关...");
			cyclicBarrier.await();
			System.out.println("玩家" + id + "进入第 2 关...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
