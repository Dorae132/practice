package com.nsb.practice.akkatest.stm;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * {@link https://blog.csdn.net/liubenlong007/article/details/53782966}
 * @author Dorae
 *
 */
public class CompanyActor extends UntypedActor {

	// 账户余额
	private Ref.View<Integer> count = STM.newRef(100);
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Coordinated) {
			
		}
	}

}
