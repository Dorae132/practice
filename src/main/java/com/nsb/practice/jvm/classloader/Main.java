package com.nsb.practice.jvm.classloader;

/**
 * 类加载器验证
 * @author Dorae
 *
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir="C:\\Users\\Dorae\\Desktop\\practice\\target\\classes";
        //创建自定义文件类加载器
        FileClassLoader loader = new FileClassLoader(rootDir);
        FileClassLoader loader2 = new FileClassLoader(rootDir);

        try {
            //加载指定的class文件,调用loadClass()
            Class<?> object1=loader.loadClass("com.nsb.practice.jvm.classloader.DemoObj");
            Class<?> object2=loader2.loadClass("com.nsb.practice.jvm.classloader.DemoObj");

            System.out.println("loadClass->obj1: " + object1.hashCode() + " # classLoader is: " + object1.getClassLoader());
            System.out.println("loadClass->obj2: " + object2.hashCode() + " # classLoader is: " + object2.getClassLoader());

            //加载指定的class文件,直接调用findClass(),绕过检测机制，创建不同class对象。
            Class<?> object3=loader.findClass("com.nsb.practice.jvm.classloader.DemoObj");
            Class<?> object4=loader2.findClass("com.nsb.practice.jvm.classloader.DemoObj");
            Class<?> object5=loader.findClass("com.nsb.practice.jvm.classloader.DemoObj");

            System.out.println("loadClass->obj3: " + object3.hashCode() + " # classLoader is: " + object3.getClassLoader());
            System.out.println("loadClass->obj4: " + object4.hashCode() + " # classLoader is: " + object4.getClassLoader());

            /**
             * 输出结果:
             * loadClass->obj1:644117698
               loadClass->obj2:644117698
               findClass->obj3:723074861
               findClass->obj4:895328852
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
