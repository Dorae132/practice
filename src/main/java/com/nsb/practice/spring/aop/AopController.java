package com.nsb.practice.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Dorae
 *
 */
@Slf4j
@RestController
public class AopController {
    
    @Autowired
    private TargetObject target;

    @RequestMapping("/aopcontroller/excute")
    public String executeTarget() {
        log.info("controller: the target class is " + target.getClass().getSimpleName());
        return target.selfMethod();
    }
}
