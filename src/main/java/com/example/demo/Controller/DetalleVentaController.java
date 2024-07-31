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

import com.example.demo.InterfazServicios.IDetalleVentaService;
import com.example.demo.Modelos.DetalleVenta;
import com.example.demo.Modelos.DTO.InfoDetalleVentaDto;

@RestController
@RequestMapping
public class DetalleVentaController {

	@Autowired
    private IDetalleVentaService _serviceDetalleVenta;

    
    @GetMapping("/detalle_ventas")
	public List<DetalleVenta> listarDetalleVentas(){
		
        List<DetalleVenta> detalleVentas = _serviceDetalleVenta.listarDetalleVentas();
		return detalleVentas;
	}
    
    @GetMapping("/infodetalleventas")
	public List<InfoDetalleVentaDto> listarInfoCarritos(){
		
        List<InfoDetalleVentaDto> detalles = _serviceDetalleVenta.listarInfoDetalleVentas();
		return detalles;
	}
	
	@GetMapping("/detalle_venta/{codigoVenta}")
	public Optional<DetalleVenta> obtenerDetalleVenta(@PathVariable int codigoVenta){
		
        return _serviceDetalleVenta.obtenerDetalleVenta(codigoVenta);
	}
	
	@PutMapping("/detalle_venta/{codigoVenta}")
	public DetalleVenta editarDetalleVenta(@PathVariable int codigoVenta, @RequestBody DetalleVenta detVenta) {
        
		DetalleVenta dVenta =  _serviceDetalleVenta.modificarDetalleVenta(codigoVenta, detVenta);
		return dVenta;
	}
	
	@PostMapping("/detalle_venta")
	public DetalleVenta crearDetalleVenta(@RequestBody DetalleVenta detVenta) {
		
        return _serviceDetalleVenta.agregarDetalleVenta(detVenta);
	}
	
	@DeleteMapping("/detalle_venta/{codigoVenta}")
	public void eliminarDetalleDeCarrito(@PathVariable int codigoVenta) {

		_serviceDetalleVenta.eliminarDetalleVenta(codigoVenta);
	}  
	
}
