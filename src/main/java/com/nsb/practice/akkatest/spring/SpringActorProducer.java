package com.nsb.practice.akkatest.spring;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class SpringActorProducer implements IndirectActorProducer {

	private ApplicationContext applicationContext;
	
	private String actorBeanName;
	
	private Object[] args;
	
	
	public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName, Object[] args) {
		super();
		this.applicationContext = applicationContext;
		this.actorBeanName = actorBeanName;
		this.args = args;
	}

	@Override
	public Class<? extends Actor> actorClass() {
		return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
	}

	@Override
	public Actor produce() {
		return (Actor) applicationContext.getBean(actorBeanName, args);
	}

}
