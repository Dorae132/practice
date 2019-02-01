package com.nsb.practice.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 打印奇数/偶数
 * 
 * @author Dorae
 *
 */
public class OddAndEvenNumber {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();
        MyNumber pointer = new MyNumber();
        pointer.setValue(1);
        new Thread(new OddThread(lock, pointer)).start();
        new Thread(new EvenThread(lock, pointer)).start();
        Thread.currentThread().sleep(10000);
    }

}

class MyNumber {
    private volatile int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
class OddThread implements Runnable {

    private Object lock;

    private MyNumber pointer;

    public OddThread(Object lock, MyNumber pointer) {
        this.lock = lock;
        this.pointer = pointer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (pointer.getValue() > 100) {
                    break;
                }
                while ((pointer.getValue() & 1) != 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println(pointer.getValue());
                pointer.setValue(pointer.getValue() + 1);
                lock.notify();
            }
        }
    }

}

class EvenThread implements Runnable {

    private Object lock;

    private MyNumber pointer;
    
    public EvenThread(Object lock, MyNumber pointer) {
        this.lock = lock;
        this.pointer = pointer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (pointer.getValue() > 100) {
                    break;
                }
                while ((pointer.getValue() & 1) != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println(pointer.getValue());
                pointer.setValue(pointer.getValue() + 1);;
                lock.notify();
            }
        }
    }

}