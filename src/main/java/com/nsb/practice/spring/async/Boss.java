package com.nsb.practice.spring.async;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAsync
public class Boss {

	private static final Logger LOGGER = LoggerFactory.getLogger(Boss.class);
	
	@Autowired
	private Worker worker;
	
	@RequestMapping("/async/dowork")
	private String doWork() throws Exception {
		long startTime = System.currentTimeMillis();
		Future<String> worker1Result = worker.doWorkAsync(2);
		Future<String> worker2Result = worker.doWorkAsync(1);
		worker1Result.get();
		worker2Result.get();
		LOGGER.info("async done: took " + (System.currentTimeMillis() - startTime) + " mills");
		
		startTime = System.currentTimeMillis();
		String worker3Result = worker.doWorkSync(2);
		String worker4Result = worker.doWorkSync(1);
		LOGGER.info("sync done: took " + (System.currentTimeMillis() - startTime) + " mills");
		return "done";
	}
}
