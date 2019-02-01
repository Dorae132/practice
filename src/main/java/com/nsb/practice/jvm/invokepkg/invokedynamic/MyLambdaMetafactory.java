package com.nsb.practice.jvm.invokepkg.invokedynamic;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.function.Supplier;

/**
 * 验证自实现callsite
 * @author Dorae
 *
 */
public class MyLambdaMetafactory {

    public static void main(String[] args) throws Throwable {
        factory();
//        lambda();
    }
    static void factory() throws Throwable {
        TestClass obj = new TestClass();
        obj.setValue("Hello World!");
        Lookup lookup = MethodHandles.lookup();
        CallSite site = LambdaMetafactory.metafactory(lookup, "get", MethodType.methodType(Supplier.class, TestClass.class),
                MethodType.methodType(Object.class), lookup.findVirtual(TestClass.class, "getTest", MethodType.methodType(String.class)),
                MethodType.methodType(String.class));
        MethodHandle target = site.getTarget();
        Supplier<String> getter = (Supplier<String>)target.invokeExact(obj);
        System.out.println(getter.get());
    }
    static void lambda() {
        TestClass obj = new TestClass();
        obj.setValue("Hello World!");
        Supplier<String> elementGetter = () -> obj.getValue();
        System.out.println(elementGetter.get());
    }
}

class TestClass {
    String value = "";

    public String getValue() {
        return value;
    }
    
    public String getTest() {
        return value + "_test";
    }

    public void setValue(String value) {
        this.value = value;
    }
}
