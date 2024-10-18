package com.example.demo.mapper;

import java.time.LocalDate;

import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.Cliente;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.DTO.InfoCarritoDto;
import com.example.demo.Modelos.DTO.InfoDetalleCarritoDto;
import com.example.demo.Modelos.DTO.ProductoCarritoDto;

public class CarritoMapper {
	
	public static DetalleDeCarrito mapToDetalleDeCarrito(ProductoCarritoDto producto) {
		
		Carrito carro = new Carrito();
		carro.setCodigoCarrito(producto.getCod_carrito());
		
		Producto prod = new Producto();
		prod.setCodpro(producto.getCod_producto());
		
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
	
	public static InfoCarritoDto mapToInfoCarritoDto(Carrito carrito) {
		
		InfoCarritoDto infoCarrito = new InfoCarritoDto();
		infoCarrito.setCodigoCarrito(carrito.getCodigoCarrito());
		infoCarrito.setFechaCreacion(carrito.getFechaCreacion());
		infoCarrito.setFechaActualizacion(carrito.getFechaActualizacion());
		infoCarrito.setNombCliente(carrito.getCliente().getCodigoPersona().getNombres());
		infoCarrito.setEstado(carrito.getEstado());
		infoCarrito.setCantidadProductos(carrito.getCantidad());
		
		return infoCarrito;
	}
	
	public static InfoDetalleCarritoDto mapToInfoDetalleCarritoDto(DetalleDeCarrito detalle) {
		
		InfoDetalleCarritoDto infoDetalle = new InfoDetalleCarritoDto();
		infoDetalle.setCodigoDetalle(detalle.getCodigoDetalle());
		infoDetalle.setCodCarrito(detalle.getCarrito().getCodigoCarrito());
		infoDetalle.setProducto(detalle.getProducto().getNombre_pro());
		infoDetalle.setCantidad(detalle.getCantidaddetalle());
		infoDetalle.setPrecioTotal(detalle.getTotal());
		
		return infoDetalle;
	
	}

}
