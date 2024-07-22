package com.example.demo.Servicios;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IDetalleDeCarrito;
import com.example.demo.InterfazServicios.IDetalleDeCarritoService;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.DTO.InfoCarritoDto;
import com.example.demo.Modelos.DTO.InfoDetalleCarritoDto;
import com.example.demo.mapper.CarritoMapper;

@Service
public class DetalleDeCarritoService implements IDetalleDeCarritoService{

    @Autowired
    private IDetalleDeCarrito _repositoryDeTalleDeCarrito;

    @Override
    public List<DetalleDeCarrito> listarDetalleDeCarritos() {

        List<DetalleDeCarrito> detalleDeCarritos = _repositoryDeTalleDeCarrito.findAll();
		return detalleDeCarritos;
    }
    
    @Override
	public List<InfoDetalleCarritoDto> listarInfoDetalleCarrito() {
		
    	List<DetalleDeCarrito> detalles = _repositoryDeTalleDeCarrito.findAll();
    	List<InfoDetalleCarritoDto> infoDetalles = detalles.stream().map(d ->{
    		InfoDetalleCarritoDto infoDetalle = new InfoDetalleCarritoDto();
    		infoDetalle = CarritoMapper.mapToInfoDetalleCarritoDto(d);
    		return infoDetalle;
    		
    	}).collect(Collectors.toList());
    	
		return infoDetalles;
	}

    @Override
    public Optional<DetalleDeCarrito> obtenerDetalleDeCarrito(int codigoDCarrito) {
      
        if( codigoDCarrito == 0){

			return null;
		}
		
		return _repositoryDeTalleDeCarrito.findById(codigoDCarrito);
    }

    @Override
    public DetalleDeCarrito modificarDetalleCarrito(int codigoDCarrito, DetalleDeCarrito deCarrito) {
        
        Optional<DetalleDeCarrito> detalleCarrito = this.obtenerDetalleDeCarrito(codigoDCarrito);

		if (detalleCarrito.isPresent()) {  

			return _repositoryDeTalleDeCarrito.save(deCarrito) ;
		}

		return null;
    }

    @Override
    public DetalleDeCarrito agregarDetalleDeCarrito(DetalleDeCarrito deCarrito) {
       
        if (deCarrito == null) {

			return null;
		}

		return _repositoryDeTalleDeCarrito.save(deCarrito);
    }

    @Override
    public void eliminarDetalleCarrito(int codigoDCarrito) {
       
        _repositoryDeTalleDeCarrito.deleteById(codigoDCarrito);
    }

	
}
