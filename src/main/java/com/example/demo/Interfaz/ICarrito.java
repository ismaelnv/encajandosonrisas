package com.example.demo.Interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Carrito;

@Repository
public interface ICarrito extends JpaRepository<Carrito, Integer> {
	
	List<Carrito> findByCliente_Codigocli(Integer codcli);
    
}
