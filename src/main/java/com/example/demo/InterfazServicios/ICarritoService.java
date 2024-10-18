package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.DTO.CarritoDto;
import com.example.demo.Modelos.DTO.InfoCarritoDto;
import com.example.demo.Modelos.DTO.ProductoCarritoDto;

@Repository
public interface ICarritoService {
    
    public List<Carrito> listarCarritos();
	public Optional<Carrito> obtenerCarrito(int codigoCarrito);
	public Carrito modificarCarrito(int codigoCarrito, Carrito carrito);
	public Carrito agregarCarrito(Carrito carrito);
	public void eliminarCarrito(int codigoCarrito);
	public CarritoDto agregarProducto(ProductoCarritoDto producto, int codigocliente);
	public List<Carrito> carritosCliente(int codcli);
	public Integer obtenerCantCarrito(Integer codcarrito);
	public List<InfoCarritoDto> listarInfoCarritos();
}
