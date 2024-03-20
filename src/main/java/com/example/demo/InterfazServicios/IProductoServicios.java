package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import com.example.demo.Modelos.Producto;

public interface IProductoServicios {

	public List<Producto> listarProductos();
	public Optional<Producto> obtenerProducto(int id);
	public Producto modificarProducto(int id, Producto p);
	public Producto agregarProducto(Producto p);
	public Producto eliminarProducto(int id);
}
