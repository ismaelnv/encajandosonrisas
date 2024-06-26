package com.example.demo.Modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "cliente")
public class Cliente {

	@Id
	private Integer codigo_cli;

	@Column( name ="fechacreacion")
	private LocalDate fechaCreacion;

	@Column(name = "fechaactualizacion")
	private LocalDate fechaActualizacion;

	@Column(name = "persona_id_per")
	private Integer personaCodigo;

	@OneToOne
	@JsonBackReference
    @JoinColumn(name = "persona_id_per",insertable = false, updatable = false)
	private Persona codigoPersona;

	@OneToMany(mappedBy = "codigoCliente")
	@JsonBackReference
    private List<Carrito> carritos;
}