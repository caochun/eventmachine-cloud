package com.example.stopwatch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class StopWatchApplication {

	public static void main(String[] args) {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(StopWatchApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);

	}

	@Bean
	public Supplier<String> hello() {
		return () -> "hello";
	}


	@Bean
	public Consumer<String> consumer() {
		return str -> System.out.println(str);
	}

}
