package com.example.demo.Modelos;

import java.time.LocalDate;

import com.example.demo.Modelos.Enum.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_per")
    private Integer idPersona;

    @Column(name = "nombres_per")
    private String nombres; 

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "dni_per")
    private String dni;

    @Column(name = "telefono_persona")
    private String telefono;

    @Column(name = "correo")
    private String correo; 

    @Column(name = "pssword")
    private String password;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActulizacion;

    @Column(name = "fecha_de_nacimiento")
    private LocalDate fechaNacimiento;   

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roles;
}
