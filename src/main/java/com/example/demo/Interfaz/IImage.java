package com.example.demo.Interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Imagen;

@Repository
public interface IImage extends JpaRepository<Imagen, Integer> {
    
}
