package com.nsb.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@SuppressWarnings("resource")
    public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/test")
	public String test(@RequestParam("value") String value) {
		LOGGER.info(value + value.length());
		System.out.println(Thread.currentThread().getContextClassLoader());
		return "success";
	}
}
