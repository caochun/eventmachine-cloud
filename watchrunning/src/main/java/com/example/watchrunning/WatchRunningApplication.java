package com.example.watchrunning;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(WatchChannels.class)
public class WatchRunningApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchRunningApplication.class, args);

    }


}