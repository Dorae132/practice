package com.nsb.practice.java.completablefuture;

/**
 * 
 * @author Dorae
 *
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {
        System.out.println(-1 << 29);
/*        (rs == SHUTDOWN && (firstTask != null || workQueue.isEmpty()))
            || rs > SHUTDOWN;*/
        /*if ((runStateAtLeast(ctl.get(), STOP) || (Thread.interrupted() && runStateAtLeast(ctl.get(), STOP)))
                && !wt.isInterrupted()) {
            
        }*/
        int i = 0;
        int j = 0;
        if (i == 0) {
            System.out.println("i: " + i);
        } else if (j == 0) {
            System.out.println("j: " + j);
        }
    }
}
