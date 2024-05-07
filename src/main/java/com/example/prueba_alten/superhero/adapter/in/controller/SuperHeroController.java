package com.example.prueba_alten.superhero.adapter.in.controller;

import com.example.prueba_alten.superhero.adapter.in.dto.SuperHeroDTO;
import com.example.prueba_alten.superhero.application.mapper.SuperHeroMapper;
import com.example.prueba_alten.superhero.application.service.SuperHeroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/superheroes")
public class SuperHeroController {
    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping
    public List<SuperHeroDTO> getAllSuperHeroes() {
        return SuperHeroMapper.INSTANCE.toDTOs(superHeroService.getAllSuperHeroes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroDTO> getSuperHeroById(@PathVariable Long id) {
        SuperHeroDTO superHeroDTO = SuperHeroMapper.INSTANCE.toDTO(superHeroService.getSuperHeroById(id));
        return superHeroDTO != null ? ResponseEntity.ok(superHeroDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<SuperHeroDTO> getSuperHeroesByNameContaining(@RequestParam String name) {
        return SuperHeroMapper.INSTANCE.toDTOs(superHeroService.getSuperHeroesByNameContaining(name));
    }

    @PostMapping
    public ResponseEntity<SuperHeroDTO> createSuperHero(@RequestBody SuperHeroDTO superHeroDTO) {
        SuperHeroDTO savedSuperHeroDTO = SuperHeroMapper.INSTANCE.toDTO(superHeroService.createSuperHero(SuperHeroMapper.INSTANCE.toEntity(superHeroDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSuperHeroDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperHeroDTO> updateSuperHero(@PathVariable Long id, @RequestBody SuperHeroDTO superHeroDTO) {
        SuperHeroDTO updatedSuperHeroDTO = SuperHeroMapper.INSTANCE.toDTO(superHeroService.updateSuperHero(id, SuperHeroMapper.INSTANCE.toEntity(superHeroDTO)));
        return updatedSuperHeroDTO != null ? ResponseEntity.ok(updatedSuperHeroDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperHero(@PathVariable Long id) {
        superHeroService.deleteSuperHero(id);
        return ResponseEntity.noContent().build();
    }
}

