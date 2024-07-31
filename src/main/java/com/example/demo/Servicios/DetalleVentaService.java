package com.example.demo.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IDetalleVenta;
import com.example.demo.InterfazServicios.IDetalleVentaService;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.DetalleVenta;
import com.example.demo.Modelos.DTO.InfoDetalleCarritoDto;
import com.example.demo.Modelos.DTO.InfoDetalleVentaDto;
import com.example.demo.mapper.CarritoMapper;
import com.example.demo.mapper.VentaMapper;

@Service
public class DetalleVentaService implements IDetalleVentaService{
	
	
	private final IDetalleVenta _repositoryDetalleVenta;
	
	public DetalleVentaService(IDetalleVenta _repositoryDetalleVenta) {
		this._repositoryDetalleVenta = _repositoryDetalleVenta;
	}

	@Override
	public List<DetalleVenta> listarDetalleVentas() {
		
		List<DetalleVenta> detalles = _repositoryDetalleVenta.findAll();
		
		return detalles;
	}
	
	@Override
	public List<InfoDetalleVentaDto> listarInfoDetalleVentas() {
		List<DetalleVenta> detalles = _repositoryDetalleVenta.findAll();
    	List<InfoDetalleVentaDto> infoDetalles = detalles.stream().map(d ->{
    		InfoDetalleVentaDto infoDetalle = new InfoDetalleVentaDto();
    		infoDetalle = VentaMapper.mapToInfoDetalleVentaDto(d);
    		return infoDetalle;
    		
    	}).collect(Collectors.toList());
    	
		return infoDetalles;
	}

	@Override
	public Optional<DetalleVenta> obtenerDetalleVenta(int codigoVenta) {
		if(codigoVenta == 0) {
			return null;
		}
		
		return _repositoryDetalleVenta.findById(codigoVenta);
	}

	@Override
	public DetalleVenta modificarDetalleVenta(int codigoVenta, DetalleVenta detVenta) {
		
		Optional<DetalleVenta> detalle = _repositoryDetalleVenta.findById(codigoVenta);
		if(detalle.isPresent()) {
			
			return _repositoryDetalleVenta.save(detVenta);
		}
		return null;
	}

	@Override
	public DetalleVenta agregarDetalleVenta(DetalleVenta detVenta) {
		
		if(detVenta == null) {
			return null;
		}
		
		return _repositoryDetalleVenta.save(detVenta);
	}

	@Override
	public void eliminarDetalleVenta(int codigoVenta) {
		
		_repositoryDetalleVenta.deleteById(codigoVenta);
		
	}

}
