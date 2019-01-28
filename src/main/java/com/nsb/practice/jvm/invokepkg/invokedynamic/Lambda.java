package com.nsb.practice.jvm.invokepkg.invokedynamic;

interface Func {

    public boolean func(String str);
}

public class Lambda {

    public void lambda(Func func) {
        return;
    }
    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        lambda.lambda(s -> {return true;});
        lambda.lambda(s -> {return false;});
    }
}
