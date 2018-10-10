package com.nsb.practice.spring.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyValue implements InitializingBean {

	@Value("${spring.redis.host}")
	private String value;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("######################initializing testvalue bean");
		
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
