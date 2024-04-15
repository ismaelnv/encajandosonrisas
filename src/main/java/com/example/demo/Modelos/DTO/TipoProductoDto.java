package com.example.demo.Modelos.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TipoProductoDto {

    private Integer codigoTP;
    private String nombre;
    private LocalDate fechaCreacion; 
    private LocalDate fechaActualizacion;
    private boolean estado;
    
}
