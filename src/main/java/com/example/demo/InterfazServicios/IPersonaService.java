package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Persona;
import com.example.demo.Modelos.DTO.CrearPersonaDto;

@Repository
public interface IPersonaService {

    public List<Persona> listarPersonas();
	public Optional<Persona> obtenerPersona(int codigoPersona);
	public Persona modificarPersona(int codigoPersona, Persona persona);
	public Persona agregarPersona(CrearPersonaDto persona);
	public void eliminarPersona(int codigoPersona);
}
