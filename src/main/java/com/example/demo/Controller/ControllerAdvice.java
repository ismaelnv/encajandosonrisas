package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Execpciones.EntidadNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(EntidadNotFoundException.class)
	public ResponseEntity<String> notFoundEx(EntidadNotFoundException e){
		
		StringBuilder sb = new StringBuilder("No se encontro ")
				.append(e.getEntidad());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(sb.toString());
	}

}
