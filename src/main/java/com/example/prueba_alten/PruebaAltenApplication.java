package com.example.prueba_alten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PruebaAltenApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaAltenApplication.class, args);
    }

}
