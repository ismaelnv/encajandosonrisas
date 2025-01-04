package com.example.demo.Interfaz;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Persona;

@Repository
public interface IPersona extends JpaRepository<Persona, Integer> {

    Optional<Persona> findBycorreo(String correo);
}
