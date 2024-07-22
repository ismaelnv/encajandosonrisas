package com.example.demo.Interfaz;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.DetalleDeCarrito;

@Repository
public interface IDetalleDeCarrito extends JpaRepository<DetalleDeCarrito, Integer> {
	
	List<DetalleDeCarrito> findByCarrito_CodigoCarrito(Integer codCarrito);
	Optional<DetalleDeCarrito> findByCarrito_CodigoCarritoAndProducto_Codpro(int codigoCarrito, int cod_pro);
}
