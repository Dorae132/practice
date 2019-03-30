package com.nsb.practice.interview.ant;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2：用多线程实现一个生产者和消费者模式。一个线程负责往Map里put 1-100的数字，
另外一个线程负责get数字并进行累加，并打印结果。
 * @author Dorae
 *
 */
public class Solution2 {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(128);
        Thread producerThread = new Thread(new Producer(map));
        Thread consumerThread = new Thread(new Consumer(map));
        consumerThread.start();
        producerThread.start();
    }
}

class Producer implements Runnable {

    private Map<Integer, Integer> map;
    
    public Producer(Map<Integer, Integer> map) {
        super();
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            map.put(i, i);
        }
    }
    
}

class Consumer implements Runnable {

    private Map<Integer, Integer> map;
    
    public Consumer(Map<Integer, Integer> map) {
        super();
        this.map = map;
    }

    @Override
    public void run() {
        int doneFlag = 0;
        while (true && doneFlag < 100) {
            Set<Entry<Integer, Integer>> entrySet = map.entrySet();
            Iterator<Entry<Integer, Integer>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<Integer, Integer> value = iterator.next();
                System.out.println(value.getValue());
                map.remove(value);
                doneFlag++;
            }
            
        }
    }
    
}