package com.nsb.practice.spring.async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * worker
 * @author Dorae
 *
 */
@Component
public class Worker {

	private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);
	
	@Async
	public Future<String> doWorkAsync(int sleepTime) {
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			LOGGER.error("worker error!", e);
			return new AsyncResult<String>("error");
		}
		return new AsyncResult<String>("success");
	}
	
	public String doWorkSync(int sleepTime) {
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			LOGGER.error("worker error!", e);
			return "error";
		}
		return "success";
	}
}
