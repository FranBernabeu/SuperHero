package com.example.prueba_alten.superhero.adapter.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.prueba_alten.superhero.adapter.in.dto.SuperHeroDTO;
import com.example.prueba_alten.superhero.application.service.SuperHeroService;
import com.example.prueba_alten.superhero.domain.model.SuperHero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SuperHeroController.class)
@AutoConfigureMockMvc
public class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperHeroService superHeroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSuperHeroes() throws Exception {
        when(superHeroService.getAllSuperHeroes()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/superheroes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetSuperHeroById() throws Exception {
        SuperHero superHero = new SuperHero();
        superHero.setId(1L);
        superHero.setName("Spiderman");
        superHero.setPower("Web-slinging");
        when(superHeroService.getSuperHeroById(1L)).thenReturn(superHero);
        mockMvc.perform(get("/api/superheroes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Spiderman"));
    }

    @Test
    public void testCreateSuperHero() throws Exception {
        SuperHeroDTO superHeroDTO = new SuperHeroDTO();
        superHeroDTO.setName("Iron Man");
        superHeroDTO.setPower("Powered Armor");
        SuperHero savedSuperHero = new SuperHero();
        savedSuperHero.setId(1L);
        savedSuperHero.setName(superHeroDTO.getName());
        savedSuperHero.setPower(superHeroDTO.getPower());
        when(superHeroService.createSuperHero(any(SuperHero.class))).thenReturn(savedSuperHero);
        mockMvc.perform(post("/api/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Iron Man"))
                .andExpect(jsonPath("$.power").value("Powered Armor"));
    }

    @Test
    public void testUpdateSuperHero() throws Exception {
        // Crear un objeto SuperHeroDTO para la inserción
        SuperHeroDTO superHeroDTOForInsert = new SuperHeroDTO();
        superHeroDTOForInsert.setName("Hulk");
        superHeroDTOForInsert.setPower("Super strength");

        // Configurar el comportamiento del servicio para la inserción
        SuperHero superHeroForInsert = new SuperHero(); // Objeto SuperHero simulado creado por el servicio
        superHeroForInsert.setId(1L);
        when(superHeroService.createSuperHero(any(SuperHero.class))).thenReturn(superHeroForInsert);

        // Realizar la inserción
        mockMvc.perform(post("/api/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTOForInsert)))
                .andExpect(status().isCreated());

        // Crear un objeto SuperHeroDTO para la actualización
        SuperHeroDTO superHeroDTOForUpdate = new SuperHeroDTO();
        superHeroDTOForUpdate.setName("Hulk Modified");
        superHeroDTOForUpdate.setPower("Super strength Modified");

        // Configurar el comportamiento del servicio para la actualización
        SuperHero updatedSuperHero = new SuperHero(); // Objeto SuperHero simulado actualizado por el servicio
        updatedSuperHero.setId(1L);
        when(superHeroService.updateSuperHero(eq(1L), any(SuperHero.class))).thenReturn(updatedSuperHero);

        // Realizar la actualización
        mockMvc.perform(put("/api/superheroes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTOForUpdate)))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteSuperHero() throws Exception {
        mockMvc.perform(delete("/api/superheroes/1"))
                .andExpect(status().isNoContent());
    }

}
