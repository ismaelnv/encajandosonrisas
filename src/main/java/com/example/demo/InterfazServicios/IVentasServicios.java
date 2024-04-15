package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Ventas;

@Repository
public interface IVentasServicios {
	
	public List<Ventas> listarVentas();
	public Optional<Ventas> obtenerVenta(int id);
	public Ventas modificarVenta(int id, Ventas v);
	public Ventas agregarVenta(Ventas v);
	public Ventas eliminarVenta(int id);

}
