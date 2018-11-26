package com.nsb.practice.akkatest.helloworld;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * 
 * @author Dorae
 * @date 2018年11月23日15:13:03
 */
public class HelloWorld extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("HelloWorld: I am starting...\nmy path is " + getSelf().path());
		ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		greeter.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("HelloWorld: I am stopping");
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Greeter.Msg.DONE) {
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("hello_world");
		ActorRef helloWorldActor = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
		TimeUnit.SECONDS.sleep(2);
		system.shutdown();
	}
}
