package com.nsb.practice.jvm;

/**
 * 类加载与实例化混合，初始化阶段介入实例化
 * @author Dorae
 *
 */
public class ClassInit {
    public static void main(String[] args) {
        staticFunction();
    }

    static ClassInit st = new ClassInit();

    static {   //静态代码块
        System.out.println("1");
    }

    {       // 实例代码块
        System.out.println("2");
    }

    ClassInit() {    // 实例构造器
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {   // 静态方法
        System.out.println("4");
    }

    int a = 110;    // 实例变量
    static int b = 112;     // 静态变量
}
