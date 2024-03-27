package com.example.demo.Interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Ventas;

@Repository
public interface IVentas extends JpaRepository<Ventas, Integer>{

}
