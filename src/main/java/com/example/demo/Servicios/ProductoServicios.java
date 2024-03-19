package com.example.demo.Servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Producto;

@Service
public class ProductoServicios implements IProductoServicios {

	private Map<Integer, Producto> data;

	public ProductoServicios() {
		Producto p1 = new Producto(1,"pizarra digital", "juguetes Estimulacion", "Unisex", "12 meses", 0.6345,
				"1 juguete interactivo", "Producto dirigido a niños para una buena estimulacion",true);
		Producto p2 = new Producto(2,"carrito de madera para construir", "juguetes multifuncion", "Unisex", "3 años", 2.50,
				"1 martillo y destornillador", "Producto dirigido a niños para promover y estimular la psicomotricidad",true);
		
		this.data = new HashMap<>();
		data.put(p1.getCod_pro(), p1);
		data.put(p2.getCod_pro(), p2);
	}

	@Override
	public List<Producto> listarProductos() {
		return data.values().stream().collect(Collectors.toList());
	}

	@Override
	public Producto obtenerProducto(int id) {
		Predicate<Producto> condicion = p -> p.getCod_pro()==id;
		return data.values().stream().filter(condicion).findFirst().orElse(null);
	}

	@Override
	public Producto modificarProducto(int id, Producto p) {
		Predicate<Producto> condicion = pro -> pro.getCod_pro()==id;
		Producto productoModificado = data.values().stream().filter(condicion).findFirst().orElse(null);
		productoModificado.setNombre_pro(p.getNombre_pro());
		productoModificado.setCategoria(p.getCategoria());
		productoModificado.setGenero(p.getGenero());
		productoModificado.setEdad_recomendada(p.getEdad_recomendada());
		productoModificado.setPeso_pro(p.getPeso_pro());
		productoModificado.setAdicionales(p.getAdicionales());
		productoModificado.setDescripcion(p.getDescripcion());
		
		return productoModificado;
	}

	@Override
	public Producto agregarProducto(Producto p) {
		Producto nuevo = new Producto();
		nuevo.setCod_pro(p.getCod_pro());
		nuevo.setNombre_pro(p.getNombre_pro());
		nuevo.setCategoria(p.getCategoria());
		nuevo.setGenero(p.getGenero());
		nuevo.setEdad_recomendada(p.getGenero());
		nuevo.setPeso_pro(p.getPeso_pro());
		nuevo.setAdicionales(p.getAdicionales());
		nuevo.setDescripcion(p.getDescripcion());
		nuevo.setEstado(true);
		
		data.put(nuevo.getCod_pro(), nuevo);
		
		return nuevo;
	}

	@Override
	public Producto eliminarProducto(int id) {
		Predicate<Producto> condicion = p -> p.getCod_pro()==id;
		Producto productoEstadoEliminado = data.values().stream()
										.filter(condicion).findFirst().orElse(null);
		
		productoEstadoEliminado.setEstado(false);
		return productoEstadoEliminado;
	}

}
