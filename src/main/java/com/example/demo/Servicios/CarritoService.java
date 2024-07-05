package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Execpciones.EntidadNotFoundException;
import com.example.demo.Interfaz.ICarrito;
import com.example.demo.Interfaz.ICliente;
import com.example.demo.Interfaz.IDetalleDeCarrito;
import com.example.demo.Interfaz.IProducto;
import com.example.demo.Interfaz.IVentas;
import com.example.demo.InterfazServicios.ICarritoService;
import com.example.demo.InterfazServicios.IDetalleDeCarritoService;
import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.Cliente;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.Ventas;
import com.example.demo.Modelos.DTO.CarritoDto;
import com.example.demo.Modelos.DTO.ClienteDto;
import com.example.demo.Modelos.DTO.ProductoCarritoDto;
import com.example.demo.Modelos.DTO.VentaDto;
import com.example.demo.mapper.CarritoMapper;

@Service
public class CarritoService implements ICarritoService{

    private final IDetalleDeCarrito _repoDetalleCarrito;
	
    private final ICarrito _repositoryCarrito;
    
    private final IProducto _repositoryProducto;
    
    private final ICliente _repositoryCliente;
    

    
    
    public CarritoService(IDetalleDeCarrito _repoDetalleCarrito, ICarrito _repositoryCarrito,
    		IProducto _repositoryProducto, ICliente _repositoryCliente) {
    	this._repoDetalleCarrito = _repoDetalleCarrito;
    	this._repositoryCarrito = _repositoryCarrito;
    	this._repositoryProducto = _repositoryProducto;
    	this._repositoryCliente = _repositoryCliente;
    	
    }
    


    @Override
    public List<Carrito> listarCarritos() {
       
        List<Carrito> carritos = _repositoryCarrito.findAll();
		return carritos;
		
    }

    @Override
    public Optional<Carrito> obtenerCarrito(int codigoCarrito) {
       
        if( codigoCarrito == 0){

			return null;
		}
		
		return _repositoryCarrito.findById(codigoCarrito);
    }

    @Override
    public Carrito modificarCarrito(int codigoCarrito, Carrito carrito) {
      
        Optional<Carrito> carritoId = this.obtenerCarrito(codigoCarrito);

		if (carritoId.isPresent()) {

			carritoId.get().setFechaActualizacion(LocalDate.now());
			return _repositoryCarrito.save(carrito);
		}

		return null;
    }

    @Override
    public Carrito agregarCarrito(Carrito carrito) {
        
        if (carrito == null) {

			return null;
		}

		return _repositoryCarrito.save(carrito);
    }

    @Override
    public void eliminarCarrito(int codigoCarrito) {
       
        _repositoryCarrito.deleteById(codigoCarrito);
    }

	@Override
	public CarritoDto agregarProducto(ProductoCarritoDto detalleCarritoDto, int codigocliente) {
		
		Boolean isNew = detalleCarritoDto.getCod_carrito() == null;
		

		validarProducto(detalleCarritoDto.getCod_producto());
		validarCarrito(detalleCarritoDto.getCod_carrito());
		validarCliente(codigocliente);
		
		CarritoDto carritoDto = new CarritoDto();
		
		if(isNew) {

			Carrito nuevo = CarritoMapper.mapToNewCarrito(codigocliente);
			agregarCarrito(nuevo);
			detalleCarritoDto.setCod_carrito(nuevo.getCodigoCarrito());

		}


		DetalleDeCarrito carritodetalle = CarritoMapper.mapToDetalleDeCarrito(detalleCarritoDto);
		_repoDetalleCarrito.save(carritodetalle);
		
		
		int cantidad = _repoDetalleCarrito.findByCarrito_CodigoCarrito(detalleCarritoDto.getCod_carrito())
						.stream().mapToInt(DetalleDeCarrito::getCantidaddetalle)
						.sum();

		
		carritoDto.setCod_carrito(detalleCarritoDto.getCod_carrito());
		carritoDto.setCantidad(cantidad);
		
		
		return carritoDto;
	}

	@Override
	public List<Carrito> carritosCliente(int codcli) {
		return null;
	}
	
	
	public Producto validarProducto(Integer codpro) {
		Optional<Producto> prod = _repositoryProducto.findById(codpro);
		
		if(prod.isEmpty()) {
			throw new EntidadNotFoundException("Producto");
		}
		return prod.get();
	}
	
	
	public Carrito validarCarrito(Integer  codcarrito) {
		
		if(codcarrito != null) {
			Optional<Carrito> carro = _repositoryCarrito.findById(codcarrito);
			
			if(carro.isEmpty()) {
				throw new EntidadNotFoundException("Carrito");
			}
			return carro.get();
		}
		
		return null;
	}
	
	public Cliente validarCliente(Integer codcli) {
		Optional<Cliente> cli = _repositoryCliente.findById(codcli);
		
		if(cli.isEmpty()) {
			throw new EntidadNotFoundException("Cliente");
		}
		return cli.get();
	}
	
	
}















