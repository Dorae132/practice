package com.nsb.practice.akkatest.lifecycle;

import java.util.concurrent.atomic.AtomicLong;

import akka.actor.UntypedActor;

/**
 * 
 * @author Dorae
 *
 */
public class MyWork extends UntypedActor {

	public static final AtomicLong count = new AtomicLong();
	
	public static enum Msg {
		WORKING, DONE, CLOSE;
	}
	
	@Override
	public void postStop() throws Exception {
		System.out.println(count.getAndIncrement() + "myWork: stopping...");
	}

	@Override
	public void preStart() throws Exception {
		System.out.println(count.getAndIncrement() + "myWork: starting...");
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Msg.WORKING) {
			System.out.println(count.getAndIncrement() + "myWork: I am working");
		} else if (msg == Msg.DONE) {
			System.out.println(count.getAndIncrement() + "myWork: stop working");
		} else if (msg == Msg.CLOSE) {
			System.out.println(count.getAndIncrement() + "myWork: stop close");
			getSender().tell(Msg.CLOSE, getSelf());
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}
	}

}
