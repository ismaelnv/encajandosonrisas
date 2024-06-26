package com.example.demo.Modelos;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "carritocompras")
public class Carrito {

    @Id
    @Column(name = "codigo_carrito")
    private Integer codigoCarrito;

    @Column(name = "fecha_reacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    private Boolean estado;

    @Column(name = "cantidad_productos")
    private Integer cantidad;

    @Column(name = "codigo_cliente")
    private Integer clienteCodigo;

    @Column(name = "codigo_venta")
    private Integer ventaCodigo;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente",insertable = false, updatable = false)
    @JsonBackReference
    private Cliente codigoCliente;

    @ManyToOne
    @JoinColumn(name = "codigo_venta",insertable = false, updatable = false)
    @JsonBackReference
    private Ventas codigoVenta;

    private Integer total;

    @OneToMany(mappedBy = "codigoCarrito")
    @JsonBackReference
	private List<DetalleDeCarrito> dcarritos;
}
