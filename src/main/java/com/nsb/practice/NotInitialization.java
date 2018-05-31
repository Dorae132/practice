package com.nsb.practice;
class ConstClass{

    static{
        System.out.println("ConstClass init!");
    }

    public static String CONSTANT = "hello world";
}

public class NotInitialization{
    public static void main(String[] args){
        System.out.println(ConstClass.CONSTANT);
    }
}