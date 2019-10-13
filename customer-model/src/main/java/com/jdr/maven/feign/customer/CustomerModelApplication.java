package com.jdr.maven.feign.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerModelApplication.class, args);
    }

}
