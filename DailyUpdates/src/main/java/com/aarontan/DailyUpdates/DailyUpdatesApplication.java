package com.aarontan.DailyUpdates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DailyUpdatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyUpdatesApplication.class, args);
	}

}
