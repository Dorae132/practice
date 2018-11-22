package com.nsb.practice.akkatest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class CountingActor extends UntypedActor {

	public static class Count {
		
	}
	
	public static class Get {
		
	}
	
	@Autowired
	private CountingService countingService;
	
	private int count = 0;
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof Count) {
			count = countingService.increment(count);
		} else if (msg instanceof Get) {
			getSender().tell(count, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
