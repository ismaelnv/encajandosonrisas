package com.example.demo.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Setter
@Getter
@Entity
@Table(name = "detalleDecarrito")
public class DetalleDeCarrito {

    @Id
    @Column(name = "codigo_detalle")
    private Integer codigoDetalle;

    @Column(name = "codigocarrito")
    private Integer carritoCodigo;

    @ManyToOne
    @JoinColumn(name = "codigocarrito",insertable = false, updatable = false)
    @JsonBackReference
    private Carrito codigoCarrito;

    @Column(name = "codproducto")
    private Integer codigoProducto;
}
