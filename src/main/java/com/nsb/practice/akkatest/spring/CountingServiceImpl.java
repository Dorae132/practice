package com.nsb.practice.akkatest.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountingServiceImpl implements CountingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountingServiceImpl.class);
	
	private static final Logger LOGGER1 = LoggerFactory.getLogger("nsb");
	
	@Override
	public int increment(int count) {
		LOGGER.info("increase " + count + " by 1.");
		LOGGER1.info("logger1 increase " + count + " by 1.");
		return count + 1;
	}

}
