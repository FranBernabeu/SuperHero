package com.example.prueba_alten.superhero.application.service;


import com.example.prueba_alten.superhero.domain.model.SuperHero;
import java.util.List;

public interface SuperHeroService {
    List<SuperHero> getAllSuperHeroes();
    SuperHero getSuperHeroById(Long id);
    List<SuperHero> getSuperHeroesByNameContaining(String name);
    SuperHero createSuperHero(SuperHero superHero);
    SuperHero updateSuperHero(Long id, SuperHero superHero);
    void deleteSuperHero(Long id);
}

