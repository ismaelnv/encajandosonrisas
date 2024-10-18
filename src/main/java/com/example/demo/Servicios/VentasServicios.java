package com.example.demo.Servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.ICarrito;
import com.example.demo.Interfaz.IDetalleDeCarrito;
import com.example.demo.Interfaz.IDetalleVenta;
import com.example.demo.Interfaz.IVentas;
import com.example.demo.InterfazServicios.IVentasServicios;
import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.DetalleVenta;
import com.example.demo.Modelos.Ventas;
import com.example.demo.Modelos.DTO.InfoVentaDto;
import com.example.demo.mapper.VentaMapper;

@Service
public class VentasServicios implements IVentasServicios{
	
	@Autowired
	private IVentas data;
	
	@Autowired
	private ICarrito _repoCarritodecompras;
	
	@Autowired
	private IDetalleDeCarrito _repoDetalleCarrito;
	
	@Autowired 
	private IDetalleVenta _repoDetalleVenta;

	@Override
	public List<Ventas> listarVentas() {
		return (List<Ventas>)data.findAll();
	}

	@Override
	public List<InfoVentaDto> listarInfoVentas() {
		List<Ventas> ventas = data.findAll();
		List<InfoVentaDto> infoVentas = ventas.stream().map(v->{
			InfoVentaDto info = new InfoVentaDto();
			info = VentaMapper.mapToInfoVenta(v);
			return info;
			
		}).collect(Collectors.toList());
		
		return infoVentas;
	}
	
	@Override
	public Optional<Ventas> obtenerVenta(int id) {
		return data.findById(id);
	}

	@Override
	public Ventas modificarVenta(int id, Ventas v) {

		Optional<Ventas> venta = this.obtenerVenta(id);
		if (venta.isPresent()) {

			return data.save(v);
		}
		return null;
	}

	@Override
	public Ventas agregarVenta(Integer codCarrito) {
		
		Optional<Carrito> carrito = _repoCarritodecompras.findById(codCarrito);
		if(carrito.isPresent()) {
			Carrito c = carrito.get();
			Ventas venta = VentaMapper.mapToVentas(c);
			
			venta.setDetVentas(new ArrayList<>());
			
			data.save(venta);
			
			List<DetalleDeCarrito> detCarrito = _repoDetalleCarrito.findByCarrito_CodigoCarrito(codCarrito);
			List<DetalleVenta> detventas = detCarrito.stream().map(dc ->{
				DetalleVenta dventa = new DetalleVenta();
				dventa.setVenta(venta);
				dventa.setProducto(dc.getProducto());
				dventa.setCantidadDetalle(dc.getCantidaddetalle());
				_repoDetalleVenta.save(dventa);
				
				venta.getDetVentas().add(dventa);
				_repoDetalleCarrito.deleteById(dc.getCodigoDetalle());
				
				return dventa;
			}).collect(Collectors.toList());
			
			
			venta.setDetVentas(detventas);
			
			_repoCarritodecompras.deleteById(codCarrito);;
			
			
			return venta;
		}

		
		return null;
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
