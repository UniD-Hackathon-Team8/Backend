package com.meltingzone.meltingzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MeltingzoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeltingzoneApplication.class, args);
    }

}
