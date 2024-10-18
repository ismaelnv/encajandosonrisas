package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;


import com.example.demo.Interfaz.ICarrito;
import com.example.demo.Interfaz.ICliente;
import com.example.demo.Interfaz.IDetalleDeCarrito;
import com.example.demo.Interfaz.IProducto;
import com.example.demo.InterfazServicios.ICarritoService;
import com.example.demo.Modelos.Carrito;
import com.example.demo.Modelos.Cliente;
import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.DTO.CarritoDto;
import com.example.demo.Modelos.DTO.InfoCarritoDto;
import com.example.demo.Modelos.DTO.ProductoCarritoDto;
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
	public List<InfoCarritoDto> listarInfoCarritos() {
		
		List<Carrito> carritos = _repositoryCarrito.findAll();
		List<InfoCarritoDto> infoCarritos = carritos.stream().map(c->{
			InfoCarritoDto info = new InfoCarritoDto();
			info = CarritoMapper.mapToInfoCarritoDto(c);
			return info;
			
		}).collect(Collectors.toList());
		
		return infoCarritos;
		
		
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
		
		// Validar que el producto y el cliente existen
		
	    validarProducto(detalleCarritoDto.getCod_producto());
	    validarCliente(codigocliente);

	    // Buscar el carrito del cliente existente o crear uno nuevo
	    
	    Optional<Carrito> carritoExistente = _repositoryCarrito.findByCliente_Codigocli(codigocliente);

	    Carrito carrito;
	    if (carritoExistente.isPresent()) {
	        carrito = carritoExistente.get();
	        carrito.setFechaActualizacion(LocalDate.now());
	    } else {
	        carrito = CarritoMapper.mapToNewCarrito(codigocliente);
	        _repositoryCarrito.save(carrito);
	    }
	    
	    detalleCarritoDto.setCod_carrito(carrito.getCodigoCarrito());

	 // Buscar detalle de carrito existente o crear uno nuevo
	    
	    Optional<DetalleDeCarrito> detalleExistente = _repoDetalleCarrito.findByCarrito_CodigoCarritoAndProducto_Codpro(
	        carrito.getCodigoCarrito(), detalleCarritoDto.getCod_producto());

	    if (detalleExistente.isPresent()) {
	        DetalleDeCarrito detalle = detalleExistente.get();
	        detalle.setCantidaddetalle(detalle.getCantidaddetalle()	);
	        _repoDetalleCarrito.save(detalle);
	    } else {
	        DetalleDeCarrito nuevoDetalle = CarritoMapper.mapToDetalleDeCarrito(detalleCarritoDto);
	        nuevoDetalle.setCarrito(carrito);
	        _repoDetalleCarrito.save(nuevoDetalle);
	    }

	    // Calcular la cantidad total de productos en el carrito
	    
	    int cantidad = _repoDetalleCarrito.findByCarrito_CodigoCarrito(carrito.getCodigoCarrito())
	                        .stream().mapToInt(DetalleDeCarrito::getCantidaddetalle)
	                        .sum();

	    CarritoDto carritoDto = new CarritoDto();
	    carritoDto.setCod_carrito(carrito.getCodigoCarrito());
	    carritoDto.setCantidad(cantidad);

	    return carritoDto;
	}
	
	@Override
	public Integer obtenerCantCarrito(Integer codCarrito) {
        return _repoDetalleCarrito.findByCarrito_CodigoCarrito(codCarrito)
                .stream().mapToInt(DetalleDeCarrito::getCantidaddetalle)
                .sum();
    }
	

	@Override
	public List<Carrito> carritosCliente(int codcli) {
		return null;
	}
	
	
	public Producto validarProducto(Integer codpro) {
		Optional<Producto> prod = _repositoryProducto.findById(codpro);
		
		if(prod.isEmpty()) {
			//throw new EntidadNotFoundException("Producto");
		}
		return prod.get();
	}
	
	
	public Carrito validarCarrito(Integer  codcarrito) {
		
		if(codcarrito != null) {
			Optional<Carrito> carro = _repositoryCarrito.findById(codcarrito);
			
			if(carro.isEmpty()) {
				//throw new EntidadNotFoundException("Carrito");
			}
			return carro.get();
		}
		
		return null;
	}
	
	public Cliente validarCliente(Integer codcli) {
		Optional<Cliente> cli = _repositoryCliente.findById(codcli);
		
		if(cli.isEmpty()) {
			//throw new EntidadNotFoundException("Cliente");
		}
		return cli.get();
	}


}















