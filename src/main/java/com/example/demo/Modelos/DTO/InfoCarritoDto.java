package com.example.demo.Modelos.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InfoCarritoDto {
	
	private Integer codigoCarrito;

    private LocalDate fechaCreacion;

    private LocalDate fechaActualizacion;

    private Boolean estado;
    
    private String nombCliente;
    
    private Integer cantidadProductos;

}
