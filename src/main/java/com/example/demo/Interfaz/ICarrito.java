package com.example.demo.Interfaz;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Carrito;

@Repository
public interface ICarrito extends JpaRepository<Carrito, Integer> {
	
	Optional<Carrito> findByCliente_Codigocli(Integer codcli);
    
}
