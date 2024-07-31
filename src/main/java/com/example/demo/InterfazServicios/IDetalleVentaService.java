package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import com.example.demo.Modelos.DetalleVenta;
import com.example.demo.Modelos.DTO.InfoDetalleVentaDto;

public interface IDetalleVentaService {
	
	public List<DetalleVenta> listarDetalleVentas();
	public Optional<DetalleVenta> obtenerDetalleVenta(int codigoVenta);
	public DetalleVenta modificarDetalleVenta(int codigoVenta, DetalleVenta detVenta);
	public DetalleVenta agregarDetalleVenta(DetalleVenta detVenta);
	public void eliminarDetalleVenta(int  codigoVenta);
	public List<InfoDetalleVentaDto> listarInfoDetalleVentas();

}
