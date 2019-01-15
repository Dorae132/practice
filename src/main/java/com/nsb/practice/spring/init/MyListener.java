package com.nsb.practice.spring.init;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean, ApplicationContextAware {

	@Autowired
	private MyValue testValue;
	
	private AtomicBoolean flag = new AtomicBoolean(false);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!flag.compareAndSet(false, true)) {
			return;
		}
		System.out.println("################## get context refresh event!");
		testValue.setValue("updated value!");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("######################initializing test bean");
		String value = testValue.getValue();
		System.out.println("##################the testValue is " + value);
		
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("###################context aware");
    }
}
