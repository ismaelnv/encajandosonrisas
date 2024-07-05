package com.example.demo.Execpciones;

import lombok.Getter;

@Getter
public class EntidadNotFoundException extends RuntimeException{
	
	private String entidad;
	
	public EntidadNotFoundException(String entidad) {
		this.entidad = entidad;
	}

}
