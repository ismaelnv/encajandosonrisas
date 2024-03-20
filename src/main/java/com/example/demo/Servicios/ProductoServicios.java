package com.example.demo.Servicios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IProducto;
import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Producto;

@Service
public class ProductoServicios implements IProductoServicios {

	@Autowired
	private IProducto data;



	@Override
	public List<Producto> listarProductos() {
		return (List<Producto>)data.findAll();
	}

	@Override
	public Optional<Producto> obtenerProducto(int id) {
		return data.findById(id);
	}

	@Override
	public Producto modificarProducto(int id, Producto p) {
		Optional<Producto> producto = this.obtenerProducto(id);
		if (producto.isPresent()) {
			return data.save(p);
		}
		return null;
		
	}

	@Override
	public Producto agregarProducto(Producto p) {
		return data.save(p);
	}

	@Override
	public Producto eliminarProducto(int id) {
	    Optional<Producto> optionalProducto = data.findById(id);
	    if (optionalProducto.isPresent()) {
	        Producto producto = optionalProducto.get();
	        producto.setEstado(false);
	        return data.save(producto);
	    }
	    return null;
	}

}
