package com.example.demo.Modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Ventas")
public class Ventas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_venta;
	private String nombre;
	private double total;
	private LocalDate fecha_venta;
	private LocalDate fecha_crea;
	private LocalDate fecha_actu;
	private boolean estado;
	private String metodo_envio;

	@OneToMany(mappedBy = "codigoVenta")
	@JsonBackReference
	private List<Carrito> carritos;
}
