package com.nsb.practice.java.concurrent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WaitNotifyQueue<Integer> queue = new WaitNotifyQueue<>();
        for (int i = 0; i < 10; i ++) {
            queue.put(i);
        }
        while (true) {
            System.out.println(queue.take());
        }
    }
}
