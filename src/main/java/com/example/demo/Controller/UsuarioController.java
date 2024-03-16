package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.InterfazServicios.IUsuarioServices;
import com.example.demo.Modelos.Usuario;

@RestController
@RequestMapping()
public class UsuarioController {
	
	@Autowired
	private IUsuarioServices service;
	
	@GetMapping("/users")
	public List<Usuario> list(){
		List<Usuario> usuarios = service.listarUsuarios();
		return usuarios;
	
	}
	
	@GetMapping("/users/{id}")
	public Usuario obtener(@PathVariable int id){
		Usuario usuario = service.obtenerUsuario(id);
		return usuario;
	
	}
	
	@PutMapping("/user/{id}")
	public Usuario editar(@PathVariable int id, @RequestBody Usuario user) {
		Usuario modificado =  service.modificarUsuario(id, user);
		return modificado;
	}
	
	@PostMapping("/users/nuevo")
	public Usuario crear(@RequestBody Usuario usu) {
		return service.agregarUsuario(usu);
	}

	@PatchMapping("/users/{id}")
	public Usuario eliminar(@PathVariable int id) {
		return service.eliminarUsuario(id);
	}

}