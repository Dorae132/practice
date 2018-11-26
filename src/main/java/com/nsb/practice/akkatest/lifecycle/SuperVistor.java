package com.nsb.practice.akkatest.lifecycle;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * 
 * @author Dorae
 *
 */
public class SuperVistor extends UntypedActor {

	/**
	 * 定义自己的监管策略
	 */
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES),
				new Function<Throwable, SupervisorStrategy.Directive>() {

					@Override
					public Directive apply(Throwable error) throws Exception {
						if (error instanceof ArithmeticException) {
							System.err.println("meet ArithmeticException ,just resume.");
							return SupervisorStrategy.resume();
						} else if (error instanceof NullPointerException) {
							System.err.println("meet NullPointerException , restart.");
							return SupervisorStrategy.restart();
						} else if (error instanceof IllegalArgumentException) {
							System.err.println("meet IllegalArgumentException ,stop.");
                            return SupervisorStrategy.stop();
						} else {
							System.out.println("escalate.");
                            return SupervisorStrategy.escalate();
						}
					}
				});
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Props) {
			ActorRef actorOf = getContext().actorOf((Props)msg, "restartActor");
		} else {
			unhandled(msg);
		}
	}

}
