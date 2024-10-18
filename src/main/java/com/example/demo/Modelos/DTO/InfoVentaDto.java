package com.example.demo.Modelos.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InfoVentaDto {
	
	private Integer codigoVenta;

    private LocalDate fechaVenta;
    
    private String nombCliente;
    
    private Integer cantidadProductos;

}
