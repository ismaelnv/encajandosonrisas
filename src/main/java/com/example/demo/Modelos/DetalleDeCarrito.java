package com.example.demo.Modelos;

//import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Setter
@Getter
@Entity
@Table(name = "detalledecarrito")
public class DetalleDeCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle")
    private Integer codigoDetalle;

//    @Column(name = "codigocarrito")
//    private Integer carritoCodigo;

    @ManyToOne
    @JoinColumn(name = "codigocarrito")
    //@JsonBackReference
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "codproducto")
    private Producto producto;
    
    @Column(name = "cantidad")
    private Integer cantidaddetalle;
}
