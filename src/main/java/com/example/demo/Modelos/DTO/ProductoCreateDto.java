package com.example.demo.Modelos.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductoCreateDto {

    private String nombre_pro;
	private Integer codigoProducto;
	private String descripcion;
	private double precio;
	private String condicion_pro;
	private String tamanio_pro;
	private String material;
	private double peso_pro;
	private int edad_recomendada;
	private double medidas;
}
