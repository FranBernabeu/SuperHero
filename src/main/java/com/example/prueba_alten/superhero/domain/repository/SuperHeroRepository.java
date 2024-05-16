package com.example.prueba_alten.superhero.domain.repository;

import com.example.prueba_alten.superhero.domain.model.SuperHero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    Page<SuperHero> findByNameContaining(String name, Pageable pageable);
}

