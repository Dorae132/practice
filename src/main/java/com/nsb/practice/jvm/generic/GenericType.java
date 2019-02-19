package com.nsb.practice.jvm.generic;

import java.util.List;

/**
 * 泛型重载？
 * @author Dorae
 *
 */
public class GenericType {

    public static String myMethod(List<String> list) {
        return "";
    }
    
    public static int myMethod(List<Integer> list) {
        return 0;
    }
}
