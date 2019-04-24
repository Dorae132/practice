package com.nsb.practice.java.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author Dorae
 * @see https://www.jianshu.com/p/55221d045f39
 */
public class FutureTaskTest {
    public static void main(String[] args) {
//        futureAndExecutorService();
//        futureTaskAndThread();
        System.out.println("end");
    }
    
    /**
     * FutureTask + Thread
     */
    public static void futureTaskAndThread() {
        Task task = new Task();
        FutureTask<Boolean> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.setDaemon(true);
        thread.setName("task thread 1");
        thread.start();
        try {
            boolean result = futureTask.get(5, TimeUnit.SECONDS);
            System.out.println("result: " + result);
        } catch (InterruptedException e) {
            System.out.println("守护线程阻塞被打断...");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("执行任务时出错...");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("执行超时...");
            e.printStackTrace();
        } catch (CancellationException e) {
            System.out.println("future 已经被cancel...");
            e.printStackTrace();
        }
    }
    
    /**
     * Future + ExecutorService
     */
    public static void futureAndExecutorService() {
        Task task = new Task();
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<Boolean> future = pool.submit(task);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("task......" + Thread.currentThread().getName() + "...i = " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(" is interrupted when calculating, will stop...");
            return false;
        }
        return true;
    }
    
}