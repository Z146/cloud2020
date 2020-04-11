package com.wuhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MainConfigClient3366 {
    public static void main(String[] args) {
        SpringApplication.run(MainConfigClient3366.class, args);
    }
}
