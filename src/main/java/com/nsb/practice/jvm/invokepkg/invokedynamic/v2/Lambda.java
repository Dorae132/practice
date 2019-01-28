package com.nsb.practice.jvm.invokepkg.invokedynamic.v2;

interface NsbFuncInterface {

    public boolean nsbFuncMethod(String str);
}

public class Lambda {

    public void lambda(NsbFuncInterface func) {
        return;
    }
    public static void main(String[] args) {
        NsbFuncInterface nsbVar = s -> {
            int i = s.length();
            return false;};
    }
}
