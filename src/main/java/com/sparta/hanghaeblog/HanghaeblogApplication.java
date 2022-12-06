package com.sparta.hanghaeblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Configuration 어노테이션을 통해 JPA에서 auditing을 가능하게 하는 어노테이션
@SpringBootApplication
public class HanghaeblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(HanghaeblogApplication.class, args);
    }

}
