package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IVentas;
import com.example.demo.InterfazServicios.IVentasServicios;
import com.example.demo.Modelos.Ventas;

@Service
public class VentasServicios implements IVentasServicios{
	
	@Autowired
	private IVentas data;

	@Override
	public List<Ventas> listarVentas() {
		return (List<Ventas>)data.findAll();
	}

	@Override
	public Optional<Ventas> obtenerVenta(int id) {
		return data.findById(id);
	}

	@Override
	public Ventas modificarVenta(int id, Ventas v) {

		Optional<Ventas> venta = this.obtenerVenta(id);
		if (venta.isPresent()) {
			v.setFecha_actu(LocalDate.now());
			return data.save(v);
		}
		return null;
	}

	@Override
	public Ventas agregarVenta(Ventas v) {

		return data.save(v);
	}

	@Override
	public Ventas eliminarVenta(int id) {
		
		Optional<Ventas> optionalVenta = data.findById(id);
	    if (optionalVenta.isPresent()) {
	    	Ventas venta = optionalVenta.get();
	        venta.setEstado(false);
	        return data.save(venta);
	    }
	    return null;
	}
	
	

}
