package com.nsb.practice.akkatest.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import akka.actor.Extension;
import akka.actor.Props;

@Component
public class SpringExt implements ApplicationContextAware, Extension {

	private ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	public Props props(String actorBeanName, Object ... args) {
		return Props.create(SpringActorProducer.class, this.context, actorBeanName, args);
	}
}
