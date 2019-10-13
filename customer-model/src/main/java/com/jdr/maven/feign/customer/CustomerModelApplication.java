package com.jdr.maven.feign.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jdr.maven.feign.customer"})
public class CustomerModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerModelApplication.class, args);
    }

}
