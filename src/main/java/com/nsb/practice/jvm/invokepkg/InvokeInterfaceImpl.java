package com.nsb.practice.jvm.invokepkg;

public class InvokeInterfaceImpl implements InvokeInterface {

    @Override
    public void invokeInterface() {
        System.out.println("InvokeInterfaceImpl#invokeInterface");
    }

    public void invokeNormalMethod() {
        System.out.println("InvokeInterfaceImpl#invokeNormalMethod");
    }
    
    public static void invokeStaticMethod() {
        System.out.println("InvokeInterfaceImpl#invokeStaticMethod");
    }
}
