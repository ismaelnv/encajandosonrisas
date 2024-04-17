package com.example.demo.Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Tipo_de_producto")
public class TipoDeProducto implements Serializable{

    @Id
    @Column(name = "codigo_tipo_producto")
    private Integer codigoTP;
    private String nombre;
    @Column(name = "fechacreacion")
    private LocalDate fechaCreacion; 
    @Column(name = "fechaactualizacion")
    private LocalDate fechaActualizacion;
    private boolean estado;
    
    @OneToMany(mappedBy = "tipoDeProducto")
    @JsonDeserialize
    private List<Producto> productos;
}
