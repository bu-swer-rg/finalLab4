package com.exam.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UniRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniRegistrationApplication.class, args);
	}

}
