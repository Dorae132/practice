package com.nsb.practice.jvm.invokepkg;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

/**
 * mh.invoke 可变参数方法句柄
 * 
 * @author Dorae
 *
 */
public class VarArgs {

    public void normalMethod(String arg1, int arg2, int[] arg3) {
        System.out.println("normalMethod");
    }

    public void asVarargsCollector() throws Throwable {
        Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class, String.class, int.class, int[].class);
        MethodHandle mh = lookup.findVirtual(VarArgs.class, "normalMethod", methodType);
        System.out.println("mh.type: " + mh.type());
        mh = mh.asCollector(int[].class, 2);
        mh.invoke(this, "hello", 2, 3, 4);
        System.out.println("mh.type: " + mh.type());
        System.out.println(methodType);
    }

    public static void main(String[] args) throws Throwable {
        VarArgs varArgs = new VarArgs();
        varArgs.asVarargsCollector();
    }
}
