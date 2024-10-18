package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.InterfazServicios.IVentasServicios;
import com.example.demo.Modelos.Ventas;
import com.example.demo.Modelos.DTO.InfoVentaDto;
import com.example.demo.Modelos.DTO.VentaDto;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class VentasController {
	
	@Autowired
	private IVentasServicios service;
	
	@GetMapping("/ventas")
	public List<Ventas> list(){

		List<Ventas> ventas = service.listarVentas();
		return ventas;
	}
	
	@GetMapping("/infoventas")
	public List<InfoVentaDto> listarInfoCarritos(){
		
        List<InfoVentaDto> ventas = service.listarInfoVentas();
		return ventas;
	}
	
	@GetMapping("/ventas/{id}")
	public Optional<Ventas> obtener(@PathVariable int id){

		return service.obtenerVenta(id);
	}
	
	@PutMapping("/ventas/{id}")
	public Ventas editar(@PathVariable int id, @RequestBody Ventas v) {

		Ventas modificado = service.modificarVenta(id, v);
		return modificado;
	}
	
	@PostMapping("/ventas")
	public Ventas crear(@RequestBody VentaDto ventaDto) {
		return service.agregarVenta(ventaDto.getCodCarrito());
	}

	@PatchMapping("/ventas/{id}")
	public Ventas eliminar(@PathVariable int id) {

		return service.eliminarVenta(id);
	}

}
