package com.wagle.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WagleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WagleApplication.class, args);
	}

}
