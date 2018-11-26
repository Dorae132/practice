package com.nsb.practice.akkatest.lifecycle;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.Option;

/**
 * restart actor
 * 
 * @author Dorae
 *
 */
public class RestartActor extends UntypedActor {

	public static enum Msg {
		DONE, RESTART;
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		super.postRestart(reason);
		System.out.println("postRestart    hashCode=" + this.hashCode());
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("postStop    hashCode=" + this.hashCode());
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		System.out.println("preStart    hashCode=" + this.hashCode());
	}

	@Override
	public void preStart() throws Exception {
		System.out.println("preRestart    hashCode=" + this.hashCode());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Msg.DONE) {
			getContext().stop(getSelf());
		} else if (msg == Msg.RESTART) {
			System.out.println(((Object) null).toString());
			// 抛出异常，默认会被restart，但这里会resume
//			 double a = 1/0;
		} else {
			unhandled(msg);
		}
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("strategy");
		ActorRef superVistor = system.actorOf(Props.create(SuperVistor.class), "superVisor");
		superVistor.tell(Props.create(RestartActor.class), ActorRef.noSender());
		ActorSelection actorSelection = system.actorSelection("akka://strategy/user/superVisor/restartActor");
		for (int i = 0; i < 1; i++) {
			actorSelection.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
		}
	}
}
