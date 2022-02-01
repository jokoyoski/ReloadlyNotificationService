package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication()
@EnableAsync
public class NotificationService {
     public static void main(String[] args) {
        SpringApplication.run(NotificationService.class, args);

    }
}