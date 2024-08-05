package com.example.demo.Modelos.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TipoDeProductoDtoActualizar {
    
    private String nombre;
    private boolean estado;
}
