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
	private int codpro;

	@Column(name = "nombre_pro")
	private String nombre_pro;

	@Column(name = "fecha_cre")
	private LocalDate fecha_cre;

	@Column(name = "fecha_actu")
	private LocalDate fecha_actu;

	@Column(name = "codigo_tipo_categoria")
	private Integer codigoProducto;
	
	@Column(columnDefinition   = "TEXT")
	private String descripcion;

	@Column(name = "precio")
	private double precio;

	@Column(name = "condicion_pro")
	private String condicion_pro;

	@Column(name = "estado")
	private boolean estado;

	@Column(name = "tamanio_pro")
	private String tamanio_pro;

	@Column(name = "material")
	private String material;

	@Column(name = "peso_pro")
	private double peso_pro;

	@Column(name = "edad_recomendada")
	private int edad_recomendada;

	@Column(name = "medidas")
	private double medidas;

	@ManyToOne
	@JoinColumn(name = "codigo_tipo_categoria",insertable = false, updatable = false)
	@JsonBackReference
	private TipoDeProducto  tipoDeProducto;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonDeserialize
    private List<Imagen>  imagenes;
}
