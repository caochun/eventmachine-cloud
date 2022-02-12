package com.example.stopwatch;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StopWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StopWatchApplication.class, args);
	}

	@Bean
	public Consumer<String> log() {
	    return str -> {
	        System.out.println("Received: " + str);
	    };
	}

}
