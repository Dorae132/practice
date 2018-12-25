package com.nsb.practice.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Dorae
 *
 */
@Slf4j
@Component
@Aspect
public class AopAspect {
    
    /**
     * aspectj是静态植入，拦截的是字节码
     * @param joinPoint
     * @param aopAnnotation
     * @return
     */
    @Around("@annotation(aopAnnotation)")
    public Object execute(ProceedingJoinPoint joinPoint, AopAnnotation aopAnnotation) {
        log.info("aspect: before advide " + joinPoint.getSignature().getDeclaringTypeName());
        Object result = "error";
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            log.error("aspect: error in advice!", e);
        }
        log.info("aspect: after advide " + joinPoint.getSignature().getDeclaringTypeName());
        return result;
    }
}
