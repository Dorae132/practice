package com.nsb.practice.jvm.ygcbugs;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * invokedynamic带来的ygc变慢的问题
 * 
 * -Xmx300M -Xms300M -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintReferenceGC
 * 
 * @author Dorae
 *
 */
public class MainEntry {

    static MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static void main(String args[]) throws InterruptedException {
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();
        Thread.currentThread().sleep(1000000000000l);
    }
    
    static class MyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                MethodType type = MethodType.methodType(double.class, double.class);
                try {
                    MethodHandle mh = lookup.findStatic(Math.class, "log", type);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

}
