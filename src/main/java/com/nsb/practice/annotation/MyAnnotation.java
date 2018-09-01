package com.nsb.practice.annotation;
import java.lang.annotation.Retention;  
/** 
 * 自定义注解 
 */  
//@Inherited  //可以被继承  
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)  //可以通过反射读取注解  
public @interface MyAnnotation {    
    String value();    
} 
