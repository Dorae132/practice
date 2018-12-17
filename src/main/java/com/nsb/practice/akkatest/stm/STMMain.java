package com.nsb.practice.akkatest.stm;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.transactor.Coordinated;
import akka.util.Timeout;
import scala.concurrent.Await;

/**
 * main
 * 
 * @author Dorae
 *
 */
public class STMMain {
	public static ActorRef COMPANYACTOR = null;
	public static ActorRef EMPLOYEEACTOR = null;

	public static void main(String[] args) throws Exception {
		ActorSystem system = ActorSystem.create("stm");
		COMPANYACTOR = system.actorOf(Props.create(CompanyActor.class), "companyActor");
		EMPLOYEEACTOR = system.actorOf(Props.create(EmployeeActor.class), "employeeActor");

		Timeout timeout = new Timeout(1, TimeUnit.SECONDS);
		for (int i = 0; i < 23; i++) {
			COMPANYACTOR.tell(new Coordinated(i, timeout), ActorRef.noSender());
			TimeUnit.MILLISECONDS.sleep(200);
			
			int companyCount = (int) Await.result(Patterns.ask(COMPANYACTOR, "getCount", timeout), timeout.duration());
			int employeeCount = (int) Await.result(Patterns.ask(EMPLOYEEACTOR, "getCount", timeout), timeout.duration());
			
			System.out.println("companyCount = " + companyCount + "; employeeCount = " + employeeCount);
			System.out.println("-----------------------");
		}
	}
}
