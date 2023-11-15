package com.phdljr.todocard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoCardApplication.class, args);
    }

}
