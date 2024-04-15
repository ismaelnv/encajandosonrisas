package com.example.demo.Modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name = "codigo_emp")
    private Integer codigoEmpleado;

    @Column(name = "sueldoEmp")
    private float sueldo;

    @Column(name = "horas_Trabajo")
    private Integer horas;

    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;

    @Column(name = "fechaActualizacion")
    private LocalDate fechaActualizacion;
    
    @Column(name = "persona_id_per")
    private Integer personaId;
    
    @OneToOne
    @JoinColumn(name = "persona_id_per",insertable = false, updatable = false)
    @JsonBackReference
    private Persona CodigoPersona;  
}
