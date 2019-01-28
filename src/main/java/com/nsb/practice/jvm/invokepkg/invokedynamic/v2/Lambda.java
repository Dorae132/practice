package com.nsb.practice.jvm.invokepkg.invokedynamic.v2;

interface NsbFuncInterface {

    public boolean nsbFuncMethod(String str);
}

interface NsbFuncInterface2 {

    public boolean nsbFuncMethod(String str, String str2);
}

public class Lambda {

    public void lambda(NsbFuncInterface func) {
        return;
    }

    public static void main(String[] args) {
        NsbFuncInterface nsbVar = s -> {
            int i = s.length();
            return false;
        };
        NsbFuncInterface nsbVar2 = s -> {
            int i = s.length();
            return false;
        };
//        NsbFuncInterface2 nsbVar2 = (s1, s2) -> {
//            return true;
//        };
        System.out.println(nsbVar);
//        System.out.println(nsbVar2);
    }
}
