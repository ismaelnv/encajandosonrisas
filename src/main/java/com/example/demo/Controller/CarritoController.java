package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.InterfazServicios.ICarritoService;
import com.example.demo.Modelos.Carrito;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {

    @Autowired
    private ICarritoService _serviceCarrito;

    @GetMapping("/carritos")
	public List<Carrito> listarCarritos(){
		
        List<Carrito> carritos = _serviceCarrito.listarCarritos();
		return carritos;
	}
	
	@GetMapping("/carritos/{codigoCarrito}")
	public Optional<Carrito> obtenerCarritoId(@PathVariable int codigoCarrito){
		
        return _serviceCarrito.obtenerCarrito(codigoCarrito);
	}
	
	@PutMapping("/carritos/{codigoCarrito}")
	public Carrito editarCarrito(@PathVariable int codigoCarrito, @RequestBody Carrito carrito) {
        
		Carrito carritoModificado =  _serviceCarrito.modificarCarrito(codigoCarrito, carrito);
		return carritoModificado;
	}
	
	@PostMapping("/carritos")
	public Carrito crearCarrito(@RequestBody Carrito carrito) {
		return _serviceCarrito.agregarCarrito(carrito);
	}

	@DeleteMapping("/carritos/{codigoCarrito}")
	public void eliminarCarrito(@PathVariable int codigoCarrito) {

		_serviceCarrito.eliminarCarrito(codigoCarrito);
	}
    
}
