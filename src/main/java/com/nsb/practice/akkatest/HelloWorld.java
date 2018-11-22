package com.nsb.practice.akkatest;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		greeter.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Greeter.Msg.DONE) {
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}
	}
	
	@Override
	public void postStop() throws Exception {
		System.out.println(getSelf().path() + "#I am stopping");
	}
}
