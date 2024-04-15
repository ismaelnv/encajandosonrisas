package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IPersona;
import com.example.demo.InterfazServicios.IPersonaService;
import com.example.demo.Modelos.Persona;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersona _repositoryPersona;

    @Override
    public List<Persona> listarPersonas() {
        
        List<Persona> personas = _repositoryPersona.findAll();
		return personas;
    }

    @Override
    public Optional<Persona> obtenerPersona(int codigoPersona) {
        
        if( codigoPersona == 0){

			return null;
		}
		
		return _repositoryPersona.findById(codigoPersona);
    }

    @Override
    public Persona modificarPersona(int codigoPersona, Persona persona) {
        
        Optional<Persona> per = this.obtenerPersona(codigoPersona);

		if (per.isPresent()) {

			per.get().setFechaActulizacion(LocalDate.now());
			return _repositoryPersona.save(persona);
		}

		return null;
    }

    @Override
    public Persona agregarPersona(Persona persona) {
       
        if (persona == null) {

			return null;
		}

		return _repositoryPersona.save(persona);
    }

    @Override
    public void eliminarPersona(int codigoPersona) {
       
        _repositoryPersona.deleteById(codigoPersona);
    }
    
}
