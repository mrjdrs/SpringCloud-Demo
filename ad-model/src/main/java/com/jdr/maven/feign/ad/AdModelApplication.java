package com.jdr.maven.feign.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdModelApplication.class, args);
    }

}
