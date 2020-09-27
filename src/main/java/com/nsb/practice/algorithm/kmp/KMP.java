package com.nsb.practice.algorithm.kmp;

import java.util.Arrays;

/**
 * @author ruhua
 * @version 1.0
 * @date 2020/9/26 19:17
 **/
public class KMP {
    public static void main(String[] args) {
        String pattern = "ABABC";
        Arrays.stream(getNextArray(pattern))
                .forEach(e -> System.out.print(e + ","));
    }
    public static int[] getNextArray(String s) {
        char[] p = s.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p [k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
