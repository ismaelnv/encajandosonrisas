package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.InterfazServicios.IPersonaService;
import com.example.demo.Modelos.Persona;

@RestController
@RequestMapping
public class PersonaController {

    @Autowired
    private IPersonaService _servicePersona;

    @GetMapping("/personas")
	public List<Persona> listarPersonas(){
		
        List<Persona> personas = _servicePersona.listarPersonas();
		return personas;
	}
	
	@GetMapping("/personas/{codigoPersona}")
	public Optional<Persona> obtenerPersonaId(@PathVariable int codigoPersona){
		
        return _servicePersona.obtenerPersona(codigoPersona);
	}
	
	@PutMapping("/personas/{codigoPersona}")
	public Persona editarPersona(@PathVariable int codigoPersona, @RequestBody Persona per) {
        
		Persona pModificado =  _servicePersona.modificarPersona(codigoPersona, per);
		return pModificado;
	}
	
	@PostMapping("/personas")
	public Persona crearPersona(@RequestBody Persona persona) {
		return _servicePersona.agregarPersona(persona);
	}

	@DeleteMapping("/personas/{codigoPersona}")
	public void eliminarPersona(@PathVariable int codigoPersona) {

		_servicePersona.eliminarPersona(codigoPersona);
	}  
}
