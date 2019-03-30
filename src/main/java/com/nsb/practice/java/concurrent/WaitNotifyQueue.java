package com.nsb.practice.java.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 利用wait notify实现队列
 * @author Dorae
 *
 */
public class WaitNotifyQueue<E> {

    private Object putLock = new Object();
    
    private Object takeLock = new Object();
    
    private Node head;
    
    private Node last;
    
    private AtomicInteger count = new AtomicInteger(0);
    
    private final Integer capacity;
    
    public WaitNotifyQueue() {
        this(Integer.MAX_VALUE);
    }

    public WaitNotifyQueue(Integer capacity) {
        this.capacity = capacity;
        head = last = new Node<E>();
    }


    public void put(E e) throws InterruptedException {
        int c = -1;
        synchronized (putLock) {
            while (count.get() >= capacity) {
                putLock.wait();
            }
            Node<E> node = new Node<>(null, e);
            this.last = this.last.next = node;
            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                putLock.notifyAll();
            }
        }
        synchronized (takeLock) {
            if (c == 0) {
                takeLock.notifyAll();
            }
        }
    }
    
    public E take() throws InterruptedException {
        E result = null;
        int c = -1;
        synchronized (takeLock) {
            while (count.get() == 0) {
                takeLock.wait();
            }
            Node<E> resultNode = head.next;
            head.next = head;
            head = resultNode;
            result = resultNode.value;
            c = count.getAndDecrement();
            if (c > 1) {
                takeLock.notifyAll();
            }
        }
        synchronized (putLock) {
            if (c == capacity) {
                putLock.notifyAll();
            }
        }
        return result;
    }
    
    static class Node<E> {
        Node next;
        E value;
        public Node() {
            super();
        }
        public Node(Node next, E value) {
            super();
            this.next = next;
            this.value = value;
        }
    }
}
