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

import com.example.demo.InterfazServicios.IDetalleDeCarritoService;
import com.example.demo.Modelos.DetalleDeCarrito;

@RestController
@RequestMapping
public class DetalleDeCarritoController {

    @Autowired
    private IDetalleDeCarritoService _serviceDetalleDeCarrito;

    
    @GetMapping("/detalle_carritos")
	public List<DetalleDeCarrito> listarDetalleDeCarritos(){
		
        List<DetalleDeCarrito> detalleDeCarritos = _serviceDetalleDeCarrito.listarDetalleDeCarritos();
		return detalleDeCarritos;
	}
	
	@GetMapping("/detalle_carritos/{codigoDCarrito}")
	public Optional<DetalleDeCarrito> obtenerDetalleCarrito(@PathVariable int codigoDCarrito){
		
        return _serviceDetalleDeCarrito.obtenerDetalleDeCarrito(codigoDCarrito);
	}
	
	@PutMapping("/detalle_carritos/{codigoDCarrito}")
	public DetalleDeCarrito editarDetalleDeCarrito(@PathVariable int codigoDCarrito, @RequestBody DetalleDeCarrito detalleDeCarrito) {
        
		DetalleDeCarrito dCarrito =  _serviceDetalleDeCarrito.modificarDetalleCarrito(codigoDCarrito, detalleDeCarrito);
		return dCarrito;
	}
	
	@PostMapping("/detalle_carritos")
	public DetalleDeCarrito crearDetalleDeCarrito(@RequestBody DetalleDeCarrito detalleDeCarrito) {
		
        return _serviceDetalleDeCarrito.agregarDetalleDeCarrito(detalleDeCarrito);
	}

	@DeleteMapping("/detalle_carritos/{codigoDCarrito}")
	public void eliminarDetalleDeCarrito(@PathVariable int codigoDCarrito) {

		_serviceDetalleDeCarrito.eliminarDetalleCarrito(codigoDCarrito);
	}  
    
}
