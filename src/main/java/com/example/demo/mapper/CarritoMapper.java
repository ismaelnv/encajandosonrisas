package com.example.demo.mapper;

import java.time.LocalDate;

import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.Cliente;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.Ventas;
import com.example.demo.Modelos.DTO.CarritoDto;
import com.example.demo.Modelos.DTO.ClienteDto;
import com.example.demo.Modelos.DTO.ProductoCarritoDto;
import com.example.demo.Modelos.DTO.VentaDto;

public class CarritoMapper {
	
	public static DetalleDeCarrito mapToDetalleDeCarrito(ProductoCarritoDto producto) {
		
		Carrito carro = new Carrito();
		carro.setCodigoCarrito(producto.getCod_carrito());
		
		Producto prod = new Producto();
		prod.setCod_pro(producto.getCod_producto());
		
		DetalleDeCarrito detalle = new DetalleDeCarrito();
		
		detalle.setCarrito(carro);
		//detalle.setCodigoCarrito(producto);
		detalle.setProducto(prod);
		detalle.setCantidaddetalle(producto.getCantidad());
		
		return detalle;
		
		
	}
	
	public static Carrito mapToNewCarrito(int codcli) {
		
		Cliente cliente = new Cliente();
		cliente.setCodigocli(codcli);
		
		
		Carrito carrito = new Carrito();
		carrito.setCliente(cliente);
		carrito.setEstado(true);
		carrito.setFechaActualizacion(LocalDate.now());
		carrito.setFechaCreacion(LocalDate.now());
		//carrito.setCantidad(5);
		//carrito.setTotal(null);
		
		return carrito;
		
	}

}
