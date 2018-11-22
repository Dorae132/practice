package com.nsb.practice.akkatest.spring;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.dsl.Inbox.Inbox;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

@RestController
public class BusinesssController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinesssController.class);
	
	@Autowired
	private ActorSystem actorSystem;
	
	@Autowired
	private SpringExt springExt;
	
	@RequestMapping("/akka/test")
	public String doAction() {
		ActorRef counter = actorSystem.actorOf(springExt.props("countingActor"), "counter" + UUID.randomUUID());
		akka.actor.Inbox inbox = Inbox.create(actorSystem);
		inbox.send(counter, new CountingActor.Count());
		inbox.send(counter, new CountingActor.Count());
		inbox.send(counter, new CountingActor.Count());
		FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
		Future<Object> ask = Patterns.ask(counter, new CountingActor.Get(), Timeout.durationToTimeout(duration));
		try { 
			return "Got back " + Await.result(ask, duration);
		} catch (Exception e) {
			LOGGER.error("", e);
			return "error";
		}
	}
}
