package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.demo.Modelos.Persona;
import com.example.demo.Modelos.DTO.CrearPersonaDto;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    @Mappings({
        @Mapping(target = "idPersona", ignore = true),
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaActulizacion", ignore = true)
    })
    Persona personaCrear(CrearPersonaDto cearPersonaDto);
}
