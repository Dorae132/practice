package com.nsb.practice.jvm.slot;

/**
 * slot对GC的影响
 * @author Dorae
 *
 */
public class SlotGC {

    static final int size = 60 * 1024 * 1024;
    
    public static void main(String[] args) {
        {
            byte[] placeHolder = new byte[size];
        }
        int a = 0;
        System.gc();
    }
}
