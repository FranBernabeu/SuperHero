package com.example.prueba_alten.superhero.application.mapper;

import com.example.prueba_alten.superhero.adapter.in.dto.SuperHeroDTO;
import com.example.prueba_alten.superhero.domain.model.SuperHero;
import java.util.List;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class SuperHeroMapperImpl implements SuperHeroMapper {

    @Override
    public SuperHero toEntity(SuperHeroDTO dto) {
        if (dto == null) {
            return null;
        }
        SuperHero superHero = new SuperHero();
        superHero.setId(dto.getId());
        superHero.setName(dto.getName());
        superHero.setPower(dto.getPower());
        return superHero;
    }

    @Override
    public SuperHeroDTO toDTO(SuperHero entity) {
        if (entity == null) {
            return null;
        }
        SuperHeroDTO superHeroDTO = new SuperHeroDTO();
        superHeroDTO.setId(entity.getId());
        superHeroDTO.setName(entity.getName());
        superHeroDTO.setPower(entity.getPower());
        return superHeroDTO;
    }

    @Override
    public List<SuperHeroDTO> toDTOs(List<SuperHero> entity) {
        if (entity == null) {
            return null;
        }
        return entity.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

