package com.example.demo.Interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.DetalleVenta;

@Repository
public interface IDetalleVenta extends JpaRepository<DetalleVenta, Integer>{
	

}
