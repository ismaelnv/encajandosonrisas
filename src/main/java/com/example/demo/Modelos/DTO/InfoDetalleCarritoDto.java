package com.example.demo.Modelos.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InfoDetalleCarritoDto {
	
	private Integer codigoDetalle;

	private Integer codCarrito;

	private String producto;
  
	private Integer cantidad;
  
	private Double precioTotal;

}
