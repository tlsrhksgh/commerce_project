package com.single.commerce_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CommerceProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommerceProjectApplication.class, args);
    }
}
