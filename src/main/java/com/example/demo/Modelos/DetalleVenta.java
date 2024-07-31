package com.example.demo.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "detalleventas")
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo_detalle;
	
	@ManyToOne
	@JoinColumn(name = "codigo_venta")
	private Ventas venta;
	
	@ManyToOne
	@JoinColumn(name = "codigo_producto")
	private Producto producto;
	
	@Column(name = "cantidad")
	private Integer cantidadDetalle;
	
	public Double getTotalVenta() {
		Double total = 0.0;
		total = producto.getPrecio()*cantidadDetalle;
		
		return total;
	}

}
