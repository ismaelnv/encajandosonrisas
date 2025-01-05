package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IPersona;
import com.example.demo.InterfazServicios.IPersonaService;
import com.example.demo.Modelos.Persona;
import com.example.demo.Modelos.DTO.CrearPersonaDto;
import com.example.demo.mapper.PersonaMapper;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersona _repositoryPersona;

    @Autowired
    private PersonaMapper _personaMapper;

   @Autowired
    private BCryptPasswordEncoder _bCryptPasswordEncoder; 

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
    public Persona agregarPersona(CrearPersonaDto persona) {
    
        Persona nuevaPersona = _personaMapper.personaCrear(persona);
        nuevaPersona.setPassword(_bCryptPasswordEncoder.encode(nuevaPersona.getPassword()));
        nuevaPersona.setFechaCreacion(LocalDate.now());
        
		return _repositoryPersona.save(nuevaPersona);
    }

    @Override
    public void eliminarPersona(int codigoPersona) {
       
        _repositoryPersona.deleteById(codigoPersona);
    }
}
