package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.TipoDeProducto;

@Repository
public interface ITipoDeProductoService {

	public List<TipoDeProducto> listarTipoProductos();
	public Optional<TipoDeProducto> obtenerTipoDeProducto(int codigotProducto);
	public TipoDeProducto modificarTipoProducto(int codigotProducto, TipoDeProducto tProducto);
	public TipoDeProducto agregarTipoProducto(TipoDeProducto tProducto);
	public void eliminarTipoDeProducto(int codigotProducto);
	public List<Producto> traerlistaDeProductosPorPrecio(TipoDeProducto tipoDeProducto);   
	public TipoDeProducto obtenerProductosPorIdTProducto(int codigoTProducto, String orden);
}
