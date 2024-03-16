package com.example.demo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

	private int cod_usu;
	private String correo;
	private String contrasena;
	private boolean estado;
}
