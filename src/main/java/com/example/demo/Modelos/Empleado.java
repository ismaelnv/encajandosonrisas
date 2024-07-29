package com.example.demo.Modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_emp")
    private Integer codigoEmpleado;

    @Column(name = "sueldo_emp")
    private Double sueldo;

    @Column(name = "horas_Trabajo")
    private Integer horas;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
    
    @Column(name = "persona_id_per")
    private Integer personaId;
    
    @OneToOne
    @JoinColumn(name = "persona_id_per",insertable = false, updatable = false)
    @JsonBackReference
    private Persona CodigoPersona;  
}
