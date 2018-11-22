package com.nsb.practice.akkatest;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaApp {
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("hello");
		actorSystem.actorOf(Props.create(HelloWorld.class));
		System.out.println("main end");
	}
}
