package com.springcloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SampleSpringClouldEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringClouldEurekaClientApplication.class, args);
	}

}
