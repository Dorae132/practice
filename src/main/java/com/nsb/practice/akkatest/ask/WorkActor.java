package com.nsb.practice.akkatest.ask;

import java.util.concurrent.TimeUnit;

import akka.actor.UntypedActor;

/**
 * WorkActor
 * @author Dorae
 *
 */
public class WorkActor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("WorkActor onReceive: " + msg);
		if (msg instanceof Integer) {
			TimeUnit.SECONDS.sleep(1);
			int i = (int) msg;
			getSender().tell(i * i, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
