package com.nsb.practice.akkatest.stm;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * {@link https://blog.csdn.net/liubenlong007/article/details/53782966}
 * 
 * @author Dorae
 *
 */
public class CompanyActor extends UntypedActor {

	// 账户余额
	private Ref.View<Integer> count = STM.newRef(100);

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Coordinated) {
			Coordinated coordinated = (Coordinated) msg;
			int downCount = (int) coordinated.getMessage();
			STMMain.EMPLOYEEACTOR.tell(coordinated.coordinate(downCount), getSelf());

			try {
				coordinated.atomic(() -> {
					if (count.get() < downCount) {
						throw new RuntimeException("余额不足");
					} else {
						STM.increment(count, -downCount);
					}
				});
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
