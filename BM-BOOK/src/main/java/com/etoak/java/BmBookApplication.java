package com.etoak.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BmBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmBookApplication.class);
    }
}
