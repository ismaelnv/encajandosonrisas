package com.example.demo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

	private int cod_cli;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String direccion;
	private String dni;
	private boolean estado;
}
