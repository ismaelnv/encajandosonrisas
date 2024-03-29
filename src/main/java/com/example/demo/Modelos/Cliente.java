package com.example.demo.Modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	private int codigo_cli;

	@Column( name ="fechacreacion")
	private Date fechaCreacion;

	@Column(name = "fechaactualizacion")
	private Date fechaActualizacion;

	@Column(name = "persona_id_per")
	private int codigoPersona;
}