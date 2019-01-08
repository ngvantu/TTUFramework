package com.ttu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ttuframework.connection.TTUConnection;
import ttuframework.connection.TTUSQLConnection;

@SpringBootApplication
public class DemoDamFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDamFrameworkApplication.class, args);
	}

	@Bean
	public TTUConnection ttuConnection(){
		TTUConnection cnn = new TTUSQLConnection("jdbc:mysql://localhost:3306/ttuframework?characterEncoding=UTF-8",
										"root", "123456");
		cnn.open();
		return cnn;
	}
}

