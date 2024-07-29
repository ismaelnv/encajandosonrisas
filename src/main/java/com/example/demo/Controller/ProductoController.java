package com.example.demo.Controller;

import java.security.InvalidKeyException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Producto;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
	
	@Autowired
	private IProductoServicios service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> list(){

		List<Producto> productos = service.listarProductos();
		return ResponseEntity.ok(productos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Producto>> obtener(@PathVariable Integer id)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		Optional<Producto> producto = service.obtenerProducto(id);
		return ResponseEntity.ok(producto);
	}
	
	@PutMapping("/{idE}/actulizar/{id}")
	public ResponseEntity<Producto> editar(@PathVariable Integer idE, @PathVariable Integer id, @RequestBody Producto pro)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{

		Producto modificado =  service.modificarProducto(idE,id, pro);
		return ResponseEntity.ok(modificado);
	}
	
	@PostMapping("/{idEmpleado}")
	public ResponseEntity<Producto> crear(@PathVariable Integer idEmpleado, @RequestBody Producto pro)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{
		
		Producto producto = service.agregarProducto(idEmpleado, pro);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}/{idEmpleado}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer idEmpleado, @PathVariable Integer id)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{

		service.eliminarProducto(idEmpleado, id);
		return ResponseEntity.noContent().build(); 
	}
}
