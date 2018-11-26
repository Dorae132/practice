package com.nsb.practice.akkatest.ask;

import akka.actor.UntypedActor;

/**
 * the actor that be used to print the result
 * @author Dorae
 *
 */
public class PrintActor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("PrintActor onReceive: " + msg);
		if (msg instanceof Integer) {
			System.out.println("print: " + msg);
		} else {
			unhandled(msg);
		}
	}

}
