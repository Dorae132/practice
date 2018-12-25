package com.nsb.practice.spring.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 目标类
 * @author Dorae
 *
 */
@Slf4j
@Component
public class TargetObject {
    
    private static String className = "";
    
    {
        className = this.getClass().getSimpleName();
    }

    @AopAnnotation
    public String targetMethod() {
        log.info("target: before target method running, the className is " + this.getClass().getSimpleName());
        return className;
    }
    
    public String selfMethod() {
        return targetMethod();
    }
}
