package com.nsb.practice.akkatest.ask;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class AskMain {
	public static void main(String[] args) throws Exception {
		ActorSystem system = ActorSystem.create("askSystem");
		ActorRef printActor = system.actorOf(Props.create(PrintActor.class), "printActor");
		ActorRef workActor = system.actorOf(Props.create(WorkActor.class), "workActor");
		// 阻塞当前线程
		Future<Object> feature = Patterns.ask(workActor, 5, 2000);
		int result = (int) Await.result(feature, Duration.create(2, TimeUnit.SECONDS));
		System.out.println("result: " + result);
		
		// 不阻塞当前线程
		Future<Object> feature1 = Patterns.ask(workActor, 8, 1000);
		Patterns.pipe(feature1, system.dispatcher()).to(printActor);
		workActor.tell(PoisonPill.getInstance(), ActorRef.noSender());
		System.out.println("main thread ready to sleep");
		TimeUnit.SECONDS.sleep(1000);
	}
}
