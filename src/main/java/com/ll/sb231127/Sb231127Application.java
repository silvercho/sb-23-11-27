package com.ll.sb231127;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate, @LastModifiedDate를 사용하기 위해 필요
public class Sb231127Application {

    public static void main(String[] args) {
        SpringApplication.run(Sb231127Application.class, args);
    }

}
