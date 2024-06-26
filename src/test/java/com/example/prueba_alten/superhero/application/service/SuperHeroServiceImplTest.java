package com.example.prueba_alten.superhero.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.Optional;

import com.example.prueba_alten.superhero.domain.model.SuperHero;
import com.example.prueba_alten.superhero.domain.repository.SuperHeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SuperHeroServiceImplTest {

    @Mock
    private SuperHeroRepository superHeroRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private SuperHeroServiceImpl superHeroService;

    @Test
    public void testGetAllSuperHeroes() {
        Pageable pageable = PageRequest.of(0, 1);
        when(superHeroRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(new SuperHero())));

        Page<SuperHero> resultPage = superHeroService.getAllSuperHeroes(0, 1);

        assertEquals(1, resultPage.getTotalElements()); // Verificar que hay un elemento en la página
        assertEquals(1, resultPage.getContent().size()); // Verificar que hay un elemento en el contenido de la página
    }

    @Test
    public void testGetSuperHeroById() {
        SuperHero superHero = new SuperHero();
        superHero.setId(1L);
        superHero.setName("Superman");
        superHero.setPower("Flight");
        when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superHero));
        assertEquals(superHero, superHeroService.getSuperHeroById(1L));
    }

    @Test
    public void testCreateSuperHero() {
        SuperHero superHero = new SuperHero();
        superHero.setName("Batman");
        superHero.setPower("Rich");
        when(superHeroRepository.save(any(SuperHero.class))).thenReturn(superHero);
        assertEquals(superHero, superHeroService.createSuperHero(superHero));
    }

    @Test
    public void testGetSuperHeroesByNameContaining() {
        when(superHeroRepository.findByNameContaining(eq("man"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(new SuperHero())));

        Page<SuperHero> resultPage = superHeroService.getSuperHeroesByNameContaining("man", 0, 1);

        assertEquals(1, resultPage.getTotalElements()); // Verificar que hay un elemento en la página
        assertEquals(1, resultPage.getContent().size()); // Verificar que hay un elemento en el contenido de la página
    }

    @Test
    public void testUpdateSuperHero() {
        SuperHero existingSuperHero = new SuperHero();
        existingSuperHero.setId(1L);
        existingSuperHero.setName("Spiderman");
        existingSuperHero.setPower("Web-slinging");
        when(superHeroRepository.existsById(1L)).thenReturn(true);
        when(superHeroRepository.save(any(SuperHero.class))).thenReturn(existingSuperHero);

        SuperHero updatedSuperHero = new SuperHero();
        updatedSuperHero.setId(1L);
        updatedSuperHero.setName("Iron Man");
        updatedSuperHero.setPower("Powered Armor");
        SuperHero result = superHeroService.updateSuperHero(1L, updatedSuperHero);

        assertEquals(updatedSuperHero.getName(), result.getName());
        assertEquals(updatedSuperHero.getPower(), result.getPower());
    }

    @Test
    public void testDeleteSuperHero() {
        doNothing().when(superHeroRepository).deleteById(1L);
        superHeroService.deleteSuperHero(1L);
        verify(superHeroRepository, times(1)).deleteById(1L);
    }
}


