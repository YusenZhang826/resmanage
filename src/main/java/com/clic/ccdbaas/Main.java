package com.clic.ccdbaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


//@EnableScheduling
//@ComponentScan(basePackages = {"com.clic.ccdbaas"})
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }
}