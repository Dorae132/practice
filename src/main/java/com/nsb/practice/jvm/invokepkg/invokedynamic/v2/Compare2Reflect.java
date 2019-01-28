package com.nsb.practice.jvm.invokepkg.invokedynamic.v2;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import org.springframework.util.StopWatch;

/**
 * methodhandler 与 reflect对比
 * 
 * @author Dorae
 *
 */
public class Compare2Reflect {

    public static void main(String[] args) throws Throwable {
        // reflect
        DynamicSample dynamicSample = new DynamicSample();
        Method add = dynamicSample.getClass().getMethod("add", int.class, int.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            add.invoke(dynamicSample, 1, 1);
        }
        System.out.println("reflect: " + (System.currentTimeMillis() - start));
        
        // dynamic
//        final MethodType mt = MethodType.fromMethodDescriptorString("(II)I", dynamicSample.getClass().getClassLoader());
        MethodType mt = MethodType.methodType(int.class, int.class, int.class);
        final MethodHandle mh = MethodHandles.lookup().findVirtual(DynamicSample.class, "add", mt);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            mh.invoke(dynamicSample, 1, 1);
        }
        System.out.println("dynamic: " + (System.currentTimeMillis() - start));
    }
}

class DynamicSample {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int add(int a, int b) {
        return a + b;
    }
}
