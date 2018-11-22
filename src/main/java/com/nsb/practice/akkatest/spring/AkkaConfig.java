package com.nsb.practice.akkatest.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

@Configuration
public class AkkaConfig {

	@Bean("actorStstem")
	public ActorSystem getActorSystem() {
		return ActorSystem.create("hello");
	}
}
