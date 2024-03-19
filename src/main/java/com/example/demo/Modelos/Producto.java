package com.example.demo.Modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto {
	
	private int cod_pro;
	private String nombre_pro;
	private String categoria;
	private String genero;
	private String edad_recomendada;
	private double peso_pro;
	private String adicionales;
	private String descripcion;
	private boolean estado;
}
