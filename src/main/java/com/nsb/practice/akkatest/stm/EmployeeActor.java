package com.nsb.practice.akkatest.stm;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * 
 * @author Dorae
 *
 */
public class EmployeeActor extends UntypedActor {

	// 员工账户
	private Ref.View<Integer> count = STM.newRef(20);
	
	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg instanceof Coordinated) {
			Coordinated coordinated = (Coordinated) msg;
			// 要增加的工资
			int upCount = (int) coordinated.getMessage();
			try {
				coordinated.atomic(() -> STM.increment(count, upCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("getCount".equals(msg)) {
			getSender().tell(count.get(), getSelf());
		} else {
			unhandled(msg);
		}
	}

}
