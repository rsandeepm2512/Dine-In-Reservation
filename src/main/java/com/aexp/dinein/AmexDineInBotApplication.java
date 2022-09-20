package com.aexp.dinein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AmexDineInBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmexDineInBotApplication.class, args);
	}

}
