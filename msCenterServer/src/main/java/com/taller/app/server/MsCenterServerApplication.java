package com.taller.app.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class MsCenterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCenterServerApplication.class, args);
	}

}
