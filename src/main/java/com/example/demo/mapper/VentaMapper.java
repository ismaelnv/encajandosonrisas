package com.example.demo.mapper;

import java.time.LocalDate;

import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.DetalleVenta;
import com.example.demo.Modelos.Ventas;
import com.example.demo.Modelos.DTO.InfoDetalleVentaDto;
import com.example.demo.Modelos.DTO.InfoVentaDto;

public class VentaMapper {
	
	public static InfoDetalleVentaDto mapToInfoDetalleVentaDto(DetalleVenta detalle) {
		
		InfoDetalleVentaDto infoDetalle = new InfoDetalleVentaDto();
		infoDetalle.setCodigoDetalle(detalle.getCodigo_detalle());
		infoDetalle.setCodVenta(detalle.getVenta().getCodigoVenta());
		infoDetalle.setProducto(detalle.getProducto().getNombre_pro());
		infoDetalle.setCantidad(detalle.getCantidadDetalle());
		infoDetalle.setPrecioTotal(detalle.getTotalVenta());
		
		return infoDetalle;
	}
	
	public static Ventas mapToVentas(Carrito carrito) {
		
		Ventas venta = new Ventas();
				
		venta.setFechaVenta(LocalDate.now());
		venta.setEstado(true);
		venta.setCliente(carrito.getCliente());
		
		return venta;
	}
	
	public static InfoVentaDto mapToInfoVenta(Ventas v) {
		InfoVentaDto infoVenta = new InfoVentaDto();
		infoVenta.setCodigoVenta(v.getCodigoVenta());
		infoVenta.setFechaVenta(v.getFechaVenta());
		infoVenta.setNombCliente(v.getCliente().getCodigoPersona().getNombres());
		infoVenta.setCantidadProductos(v.getCantidad());
		
		return infoVenta;
	}
	
	
}
