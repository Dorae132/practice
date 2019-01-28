package com.nsb.practice.jvm.invokepkg;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class ContrastMethodHandleAndRelection {

    final static Long COUNT = 100000000L;

    // -XX:CompileThreshold=500 -XX:+PrintCompilation
    public static Object reflection(Method method) throws Exception {
        int result = (int) method.invoke("abc");
        return Math.multiplyExact(result, Long.BYTES);
    }

    public static Object methodHandle(MethodHandle mh) throws Throwable {
        int result = (int) mh.invoke("abc");
        return Math.multiplyExact(result, Long.BYTES);
    }

    public static void main(String[] args) throws Throwable {
        String methodName = "length";
        Method method = String.class.getDeclaredMethod(methodName);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(int.class);
        MethodHandle mh = lookup.findVirtual(String.class, methodName, type);
        // 预热
        for (int i = 0; i < COUNT; i++) {
            reflection(method);
            methodHandle(mh);
        }

        long time = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            reflection(method);
        }
        System.err.println(COUNT + " times Reflection spent [" + (System.currentTimeMillis() - time) + "]ms");

        time = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            methodHandle(mh);
        }
        System.err.println(COUNT + " times MethodHandle spent [" + (System.currentTimeMillis() - time) + "]ms");
    }
}
