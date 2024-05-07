package com.example.prueba_alten.superhero.domain.repository;

import com.example.prueba_alten.superhero.domain.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    List<SuperHero> findByNameContaining(String name);
}

