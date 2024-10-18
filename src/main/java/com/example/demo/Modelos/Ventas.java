package com.example.demo.Modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Setter
@Getter
@Entity
@Table(name = "ventas")
public class Ventas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_venta")
	private Integer codigoVenta;
	
	@Column(name = "fecha_venta")
	private LocalDate fechaVenta;
	
	@Column(name = "estado")
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venta")
	@JsonBackReference
	private List<DetalleVenta> detVentas;
	
	
	public Integer getCantidad() {
    	Integer canttotal = 0;
    	for (DetalleVenta detalleVenta : detVentas) {
			canttotal += detalleVenta.getCantidadDetalle();
		}
    	return canttotal;
    }
	
//	@OneToOne
//	@JoinColumn(name = "codigo_carrito", unique = true)
//	private Carrito carrito;

//	@OneToMany(mappedBy = "codigoVenta")
//	@JsonBackReference
//	private List<Carrito> carritos;
}
