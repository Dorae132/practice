package com.nsb.practice.jvm.invokepkg;

public class JavaCommonInvokeInstruction {

    public void invoke() {
        // invoke special
        InvokeInterface sample = new InvokeInterfaceImpl();
        // invokeinterface
        sample.invokeInterface();
        InvokeInterfaceImpl impl = new InvokeInterfaceImpl();
        // invokevirtual
        impl.invokeNormalMethod();
        // invokestatic
        InvokeInterfaceImpl.invokeStaticMethod();
        methodA("");
        methodA(1);
    }
    
    public void methodA(String obj) {
        
    }
    
    public void methodA(Integer obj) {
        
    }
}
