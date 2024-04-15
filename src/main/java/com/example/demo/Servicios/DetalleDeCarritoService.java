package com.example.demo.Servicios;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IDetalleDeCarrito;
import com.example.demo.InterfazServicios.IDetalleDeCarritoService;
import com.example.demo.Modelos.DetalleDeCarrito;

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
