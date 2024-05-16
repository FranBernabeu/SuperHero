package com.example.prueba_alten.superhero.application.mapper;

import com.example.prueba_alten.superhero.domain.model.SuperHero;
import com.example.prueba_alten.superhero.adapter.in.dto.SuperHeroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface SuperHeroMapper {

    SuperHeroMapper INSTANCE = Mappers.getMapper(SuperHeroMapper.class);

    @Mapping(target = "id", ignore = true)
    SuperHero toEntity(SuperHeroDTO dto);

    SuperHeroDTO toDTO(SuperHero entity);

    Page<SuperHeroDTO> toDTOs(Page<SuperHero> entity);
}

