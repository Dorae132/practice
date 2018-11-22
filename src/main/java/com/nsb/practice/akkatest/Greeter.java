package com.nsb.practice.akkatest;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

	public static enum Msg {
		GREET, DONE;
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Msg.GREET) {
			System.out.println("hello world, father!");
			getSender().tell(Msg.DONE, getSelf());
		} else {
			unhandled(msg);
		}
	}

	@Override
	public void postStop() throws Exception {
		System.out.println(getSelf().path() + "#I am stopping");
	}

	
}
