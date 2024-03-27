package com.example.demo.Modelos;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Producto")
public class Producto {
	
	@Id
	 private int cod_pro ;
	 private String nombre_pro ;
	 private Date fecha_cre ;
	 private LocalDate fecha_actu ;
	 
	 @Column(columnDefinition   = "TEXT")
	 private String descripcion ;
	 private double precio ;
	 private String condicion_pro ;
	 private boolean estado ;
	 private String imagen_pro ;
	 private String tamanio_pro ;
	 private int codigo_tipo_categoria ;
	 private String material;
	 private double peso_pro ;
	 private int edad_recomendada ;
	 private double medidas ;

	 
}
