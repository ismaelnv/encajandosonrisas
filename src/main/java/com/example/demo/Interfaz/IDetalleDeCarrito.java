package com.example.demo.Interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.DetalleDeCarrito;

@Repository
public interface IDetalleDeCarrito extends JpaRepository<DetalleDeCarrito, Integer> {
	
	List<DetalleDeCarrito> findByCarrito_CodigoCarrito(Integer codCarrito);
}
