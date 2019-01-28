package com.nsb.practice.jvm.invokepkg;

import java.lang.reflect.Method;

/**
 * 反射调用方法
 * @author Dorae
 *
 */
public class ReflectionMain {

    public void reflection() throws Exception {
        String methodName = "length";
        Method method = String.class.getDeclaredMethod(methodName);
        Object result = method.invoke("abc");
    }
}
