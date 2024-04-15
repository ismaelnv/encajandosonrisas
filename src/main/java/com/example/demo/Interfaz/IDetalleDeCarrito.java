package com.example.demo.Interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.DetalleDeCarrito;

@Repository
public interface IDetalleDeCarrito extends JpaRepository<DetalleDeCarrito, Integer> {

}
