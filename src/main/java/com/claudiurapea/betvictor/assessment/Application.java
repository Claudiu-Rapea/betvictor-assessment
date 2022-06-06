package com.claudiurapea.betvictor.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJpaAuditing
@EnableJms
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
