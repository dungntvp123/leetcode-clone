package com.college.leetcodeclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@PropertySource({"classpath:server.properties"})
public class LeetcodeCloneApplication {
	public static void main(String[] args) {
		SpringApplication.run(LeetcodeCloneApplication.class, args);
	}

}
