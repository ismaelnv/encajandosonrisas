package com.example.demo.Modelos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoDetalleVentaDto {
	
	private Integer codigoDetalle;

	private Integer codVenta;

	private String producto;
  
	private Integer cantidad;
  
	private Double precioTotal;

}
