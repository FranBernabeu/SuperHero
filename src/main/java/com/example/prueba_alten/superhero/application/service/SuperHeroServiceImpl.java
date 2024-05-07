package com.example.prueba_alten.superhero.application.service;

import com.example.prueba_alten.superhero.domain.model.SuperHero;
import com.example.prueba_alten.superhero.domain.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroRepository;

    @Autowired
    public SuperHeroServiceImpl(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    @Override
    public List<SuperHero> getAllSuperHeroes() {
        return superHeroRepository.findAll();
    }

    @Override
    public SuperHero getSuperHeroById(Long id) {
        return superHeroRepository.findById(id).orElse(null);
    }

    @Override
    public List<SuperHero> getSuperHeroesByNameContaining(String name) {
        return superHeroRepository.findByNameContaining(name);
    }

    @Override
    public SuperHero createSuperHero(SuperHero superHero) {
        return superHeroRepository.save(superHero);
    }

    @Override
    public SuperHero updateSuperHero(Long id, SuperHero superHero) {
        if (superHeroRepository.existsById(id)) {
            superHero.setId(id);
            superHeroRepository.save(superHero);
            return superHero;
        }
        return null;
    }

    @Override
    public void deleteSuperHero(Long id) {
        superHeroRepository.deleteById(id);
    }
}

