package com.nsb.practice.akkatest.helloworld;

import java.util.concurrent.TimeUnit;

import akka.actor.UntypedActor;

/**
 * 	
 * @author Dorae
 *
 */
public class Greeter extends UntypedActor {

	public static enum Msg {
		GREET, DONE;
	}
	
	@Override
	public void postStop() throws Exception {
		System.out.println("Greeter: I am stopping");
	}

	@Override
	public void preStart() throws Exception {
		System.out.println("Greeter: I am starting\nmy path is " + getSelf().path());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Msg.GREET) {
			System.out.println("Greeter: Hello World!");
			TimeUnit.SECONDS.sleep(1);
			getSender().tell(Msg.DONE, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
