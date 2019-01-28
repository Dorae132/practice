package com.nsb.practice.jvm.invokepkg;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

/**
 * 方法句柄调用
 * 
 * @author Dorae
 *
 */
public class InvokeExact {

    public void invokeExact(int i) throws Throwable {
        Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", methodType);
        // 1. methodType 与methodHandle必须匹配
        String result = (String) mh.invokeExact("Hello World", 0, 3);
    }
    
    public void generateMethodTypesFromDescriptor() throws Throwable {
        ClassLoader cl = this.getClass().getClassLoader();
        String descriptor = "(I)Ljava/lang/String;";
        MethodType mt1 = MethodType.fromMethodDescriptorString(descriptor, cl);
        // 2. methodType 与handle可以不匹配
        String result = (String) MethodHandles.lookup().findStatic(String.class, "valueOf", mt1).invoke(123);
        System.out.println(result);
    }
    public static void main(String[] args) throws Throwable {
        InvokeExact invokeExact = new InvokeExact();
        invokeExact.generateMethodTypesFromDescriptor();
    }
}
