package com.nsb.practice.akkatest.procedure;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

/**
 * procdure test
 * @author Dorae
 *
 */
public class ProcedureTest extends UntypedActor {

	Procedure<Object> happy = new Procedure<Object>() {

		@Override
		public void apply(Object msg) throws Exception {
			System.out.println("I am happy: " + msg);
			if (msg == Msg.PLAY) {
				getSender().tell("I am already happy!", getSelf());
				System.out.println("I am already happy!");
			} else if (msg == Msg.SLEEP) {
				getContext().become(angray);
				System.out.println("I do not like sleep!");
			} else {
				unhandled(msg);
			}
		}
	};
	
	Procedure<Object> angray = new Procedure<Object>() {

		@Override
		public void apply(Object msg) throws Exception {
			System.out.println("I am angray: " + msg);
			if (msg == Msg.SLEEP) {
				getSender().tell("I am already angray!", getSelf());
				System.out.println("I am already angray!");
			} else if (msg == Msg.PLAY) {
				getContext().become(happy);
				System.out.println("I like play!");
			}
		}
	};
	
	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("on receive msg: " + msg);
		if (msg == Msg.SLEEP) {
			getContext().become(angray);
		} else if (msg == Msg.PLAY) {
			getContext().become(happy);
		} else {
			unhandled(msg);
		}
	}

	public static enum Msg {
		SLEEP, PLAY;
	}
	
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("strategy");
		ActorRef procedure = system.actorOf(Props.create(ProcedureTest.class), "procedureTest");
		procedure.tell(Msg.PLAY, ActorRef.noSender());
		procedure.tell(Msg.SLEEP, ActorRef.noSender());
		procedure.tell(Msg.PLAY, ActorRef.noSender());
		procedure.tell(Msg.PLAY, ActorRef.noSender());

		procedure.tell(PoisonPill.getInstance(), ActorRef.noSender());
	}
}
