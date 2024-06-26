package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Producto;

@Repository
public interface IProductoServicios {

	public List<Producto> listarProductos();
	public Optional<Producto> obtenerProducto(int id);
	public Producto modificarProducto(int id, Producto p);
	public Producto agregarProducto(Producto p);
	public Producto eliminarProducto(int id);
	public void eliminarProductoDelSistema(Integer id);
}
