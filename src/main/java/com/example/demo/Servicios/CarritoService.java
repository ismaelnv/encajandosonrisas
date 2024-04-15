package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.ICarrito;
import com.example.demo.InterfazServicios.ICarritoService;
import com.example.demo.Modelos.Carrito;

@Service
public class CarritoService implements ICarritoService{

    @Autowired
    private ICarrito _repositoryCarrito;

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
}
