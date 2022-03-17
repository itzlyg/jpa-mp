package com.jpamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class JpampApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpampApplication.class, args);
	}
}
