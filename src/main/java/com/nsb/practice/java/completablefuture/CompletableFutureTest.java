package com.nsb.practice.java.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 
 * @author Dorae
 *
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> f1 = CompletableFuture.completedFuture("123");
        System.out.println(f1.isDone() + "_" + f1.get());
    }
}
