package com.example.prueba_alten.superhero.application.service;

import com.example.prueba_alten.superhero.domain.model.SuperHero;
import com.example.prueba_alten.superhero.domain.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public SuperHeroServiceImpl(SuperHeroRepository superHeroRepository, KafkaProducerService kafkaProducerService) {
        this.superHeroRepository = superHeroRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    @Cacheable("superheroes")
    public Page<SuperHero> getAllSuperHeroes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return superHeroRepository.findAll(pageable);
    }

    @Override
    @Cacheable("superheroe")
    public SuperHero getSuperHeroById(Long id) {
        return superHeroRepository.findById(id).orElse(null);
    }

    @Override
    public Page<SuperHero> getSuperHeroesByNameContaining(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return superHeroRepository.findByNameContaining(name, pageable);
    }

    @Override
    public SuperHero createSuperHero(SuperHero superHero) {
        SuperHero savedSuperHero = superHeroRepository.save(superHero);
        kafkaProducerService.sendMessage("Created SuperHero: " + savedSuperHero);
        return savedSuperHero;
    }

    @Override
    public SuperHero updateSuperHero(Long id, SuperHero superHero) {
        if (superHeroRepository.existsById(id)) {
            superHero.setId(id);
            SuperHero updateSuperHero = superHeroRepository.save(superHero);
            kafkaProducerService.sendMessage("Updated SuperHero: " + updateSuperHero);
            return superHero;
        }
        return null;
    }

    @Override
    public void deleteSuperHero(Long id) {
        superHeroRepository.deleteById(id);
        kafkaProducerService.sendMessage("Deleted SuperHero with id: " + id);
    }
}

