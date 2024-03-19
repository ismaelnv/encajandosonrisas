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

import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Producto;

@RestController
@RequestMapping
public class ProductoController {
	
	@Autowired
	private IProductoServicios service;
	
	@GetMapping("/productos")
	public List<Producto> list(){
		List<Producto> productos = service.listarProductos();
		return productos;
	
	}
	
	@GetMapping("/productos/{id}")
	public Producto obtener(@PathVariable int id){
		Producto producto = service.obtenerProducto(id);
		return producto;
	
	}
	
	@PutMapping("/productos/{id}")
	public Producto editar(@PathVariable int id, @RequestBody Producto pro) {
		Producto modificado =  service.modificarProducto(id, pro);
		return modificado;
	}
	
	@PostMapping("/productos/nuevo")
	public Producto crear(@RequestBody Producto pro) {
		return service.agregarProducto(pro);
	}

	@PatchMapping("/productos/{id}")
	public Producto eliminar(@PathVariable int id) {
		return service.eliminarProducto(id);
	}

}
