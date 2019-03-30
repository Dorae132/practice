package com.nsb.practice.interview.ant;

import java.util.concurrent.TimeUnit;

/**
 * 1：实现两个线程，使之交替打印1-100,如：两个线程分别为：Printer1和Printer2,最后输出结果为： 
Printer1 — 1 Printer2 一 2 Printer1 一 3 Printer2 一 4
 * @author Dorae
 *
 */
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MyNumber number = new MyNumber(1);
        Object lockObj = new Object();
        new Thread(new ThreadA(lockObj, number)).start();
        new Thread(new ThreadB(lockObj, number)).start();
        TimeUnit.SECONDS.sleep(10000);
    }
}

class MyNumber {
    private volatile int v;

    public MyNumber(int v) {
        super();
        this.v = v;
    }

    public int getV() {
        return v;
    }
    
    public void add(int diff) {
        v += diff;
    }
}

/**
 * 奇数
 * @author Dorae
 *
 */
class ThreadA implements Runnable {

    private Object lockObj;
    
    private MyNumber number;
    
    public ThreadA(Object lockObj, MyNumber number) {
        super();
        this.lockObj = lockObj;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lockObj) {
                while ((number.getV() & 1) == 0) {
                    try {
                        lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (number.getV() <= 99) {
                    System.out.println("Printer1 - " + number.getV());
                    number.add(1);
                    lockObj.notify();
                } else {
                    break;
                }
            }
        }
    }
    
}

/**
 * 偶数
 * @author Dorae
 *
 */
class ThreadB implements Runnable {

    private Object lockObj;
    
    private MyNumber number;
    
    public ThreadB(Object lockObj, MyNumber number) {
        super();
        this.lockObj = lockObj;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lockObj) {
                while ((number.getV() & 1) != 0) {
                    try {
                        lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (number.getV() <= 100) {
                    System.out.println("Printer2 - " + number.getV());
                    number.add(1);
                    lockObj.notify();
                } else {
                    break;
                }
            }
        }
    }
    
}
