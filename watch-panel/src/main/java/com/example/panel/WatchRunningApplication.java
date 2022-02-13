package com.example.watchrunning;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WatchRunningApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchRunningApplication.class, args);

    }


    @GetMapping("/send")
    public String greeting(@RequestParam(name = "event", required = false, defaultValue = "World") String event) {
        return "sent";
    }


    @Bean
    public Consumer<String> consumer() {
        return str -> System.out.println(str);
    }
}