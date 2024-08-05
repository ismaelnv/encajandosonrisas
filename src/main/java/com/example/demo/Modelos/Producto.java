package com.example.demo.Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Producto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_pro;
	private String nombre_pro;
	private LocalDate fecha_cre;
	private LocalDate fecha_actu;
	@Column(name = "codigo_tipo_categoria")
	private Integer codigoProducto;
	@Column(columnDefinition   = "TEXT")
	private String descripcion;
	private double precio;
	private String condicion_pro;
	private boolean estado;
	private String imagen_pro;
	private String tamanio_pro;
	private String material;
	private double peso_pro;
	private int edad_recomendada;
	private double medidas;

	@ManyToOne
	@JoinColumn(name = "codigo_tipo_categoria",insertable = false, updatable = false)
	@JsonBackReference
	private TipoDeProducto  tipoDeProducto;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonDeserialize
    private List<Imagen>  imagenes;
}
