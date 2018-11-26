package com.nsb.practice.akkatest.router;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.collect.Lists;
import com.nsb.practice.akkatest.inbox.InboxTest;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

/**
 * router
 * 
 * @author Dorae
 *
 */
public class RouterTest extends UntypedActor {

	private Router router;

	public static AtomicBoolean flag = new AtomicBoolean(true);

	{
		List<Routee> routees = Lists.newArrayList();
		for (int i = 0; i < 5; i++) {
			ActorRef worker = getContext().actorOf(Props.create(InboxTest.class), "worker_" + i);
			getContext().watch(worker);
			routees.add(new ActorRefRoutee(worker));
		}
		router = new Router(new RoundRobinRoutingLogic(), routees);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof InboxTest.Msg) {
			// 转发
			router.route(msg, getSender());
		} else if (msg instanceof Terminated) {
			ActorRef sourceActor = ((Terminated) msg).actor();
			router = router.removeRoutee(sourceActor);
			int routerCount = router.routees().toList().size();
			System.out.println(sourceActor.path() + " 该actor已经删除。router.size=" + routerCount);
			if (routerCount == 0) {
				System.out.println("没有可用的actor，关闭系统！！！");
				flag.compareAndSet(true, false);
				getContext().system().shutdown();
			}
		} else {
			unhandled(msg);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("starategy");
		ActorRef router = system.actorOf(Props.create(RouterTest.class), "router");
		int i = 1;
		while (flag.get()) {
			router.tell(InboxTest.Msg.WORKING, ActorRef.noSender());
			if (i % 10 == 0) {
				router.tell(InboxTest.Msg.CLOSE, ActorRef.noSender());
			}
			TimeUnit.MILLISECONDS.sleep(500);
			i++;
		}
	}
}
