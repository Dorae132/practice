package com.nsb.practice.jvm.override;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * java 7 引入的动态类型支持，invokedynamic指令 <br>
 * p262, <br>
 * 声明函数，绑定对象，将动态绑定的过程用代码实现，相当于实现了invokedynamic指令
 * @author Dorae
 *
 */
public class MethodHandleTest {

    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("nsb");
    }
    
    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }
}
