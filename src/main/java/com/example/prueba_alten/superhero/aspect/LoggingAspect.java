package com.example.prueba_alten.superhero.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.prueba_alten.superhero.adapter.in.controller.SuperHeroController.getSuperHeroById(..)) && args(id,..)")
    public void logBefore(Long id) {
        if (id < 0) {
            System.out.println("Attempt to retrieve superheroe with negative id: " + id);
        }
    }
}

