package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Producto;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
	
	@Autowired
	private IProductoServicios service;
	
	@GetMapping
	public List<Producto> list(){

		List<Producto> productos = service.listarProductos();
		return productos;
	}
	
	@GetMapping("/{id}")
	public Optional<Producto> obtener(@PathVariable Integer id){
		
		return service.obtenerProducto(id);
	}
	
	@PutMapping("/{id}")
	public Producto editar(@PathVariable int id, @RequestBody Producto pro) {
		Producto modificado =  service.modificarProducto(id, pro);
		return modificado;
	}
	
	@PostMapping
	public Producto crear(@RequestBody Producto pro) {
		return service.agregarProducto(pro);
	}

	@PatchMapping
	public Producto eliminar(@PathVariable int id) {

		return service.eliminarProducto(id);
	}

	@DeleteMapping("{id}")
	public void eliminarProductoDelSistema(@PathVariable Integer id){

		service.eliminarProductoDelSistema(id);
	}
	
}
