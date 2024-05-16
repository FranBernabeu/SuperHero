package com.example.prueba_alten.superhero.application.service;


import com.example.prueba_alten.superhero.domain.model.SuperHero;
import org.springframework.data.domain.Page;

public interface SuperHeroService {
    Page<SuperHero> getAllSuperHeroes(int page, int size);
    SuperHero getSuperHeroById(Long id);
    Page<SuperHero> getSuperHeroesByNameContaining(String name, int page, int size);
    SuperHero createSuperHero(SuperHero superHero);
    SuperHero updateSuperHero(Long id, SuperHero superHero);
    void deleteSuperHero(Long id);
}

