package com.etoak.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */

@SpringBootApplication
@EnableFeignClients
public class BmDonateApplication {
    public static void main(String[] args){
        SpringApplication.run(BmDonateApplication.class);
    }
}
