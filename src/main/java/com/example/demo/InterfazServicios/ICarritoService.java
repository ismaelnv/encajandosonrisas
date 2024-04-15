package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Carrito;

@Repository
public interface ICarritoService {
    
    public List<Carrito> listarCarritos();
	public Optional<Carrito> obtenerCarrito(int codigoCarrito);
	public Carrito modificarCarrito(int codigoCarrito, Carrito carrito);
	public Carrito agregarCarrito(Carrito carrito);
	public void eliminarCarrito(int codigoCarrito);
}
