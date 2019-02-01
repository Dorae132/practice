package com.nsb.practice.jvm.inline;

/**
 * -XX:MaxFreqInlineSize=10 -XX:MaxInlineSize=1024 -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 * @author Dorae
 *
 */
public class InlineMain {
    public static void main(String[] args) {
        InlineMain obj = new InlineMain();
        for (int i = 0; i < 100; i++) {
            obj.add2(i, i, i, i);
        }
    }
    
    private int add1(int x1, int x2) {
        return x1 + x2;
    }
    
    private int add2(int x1, int x2, int x3, int x4) {
        return add1(x1, x2) + add1(x3, x4);
    }
}
