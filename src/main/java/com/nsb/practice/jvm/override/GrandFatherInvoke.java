package com.nsb.practice.jvm.override;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * 实现调用祖类方法 https://blog.csdn.net/hj7jay/article/details/73480386
 * https://blog.csdn.net/zxhoo/article/details/38387141
 * 
 * @author Dorae
 *
 */
public class GrandFatherInvoke {
    class GrandFather {
        void thinking() {
            System.out.println("I am grandFather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("I am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {
                // 1.7
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, Son.class);
                mh.invoke(this);

                // 1.8
//                 MethodType mt = MethodType.methodType(void.class);
//                 Field IMPL_LOOKUP =
//                 MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
//                 IMPL_LOOKUP.setAccessible(true);
//                 MethodHandles.Lookup lkp = (MethodHandles.Lookup) IMPL_LOOKUP.get(null);
//                 MethodHandle h1 = lkp.findSpecial(GrandFather.class, "thinking", mt,
//                 GrandFather.class);
//                 h1.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        (new GrandFatherInvoke().new Son()).thinking();
    }
}
