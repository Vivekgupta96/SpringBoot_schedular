package com.masai.SpringBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SpringBootAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootAppApplication.class, args);
		System.out.println("application started...");
	}

}
