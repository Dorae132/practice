package com.nsb.practice.akkatest.lifecycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

/**
 * 
 * @author Dorae
 *
 */
public class WatchActor extends UntypedActor {

	public WatchActor(ActorRef actorRef) {
		getContext().watch(actorRef);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Terminated) {
			System.err.println(MyWork.count.getAndIncrement() + "" + ((Terminated)msg).getActor().path() + " has terminated. now shutdown the system.");
			getContext().system().shutdown();
		} else {
			unhandled(msg);
		}
	}
	
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("hello");
		ActorRef myWork = system.actorOf(Props.create(MyWork.class), "myWork");
		ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, myWork), "watchActor");
		myWork.tell(MyWork.Msg.WORKING, ActorRef.noSender());
		myWork.tell(MyWork.Msg.DONE, ActorRef.noSender());
		myWork.tell(PoisonPill.getInstance(), ActorRef.noSender());
	}
}
