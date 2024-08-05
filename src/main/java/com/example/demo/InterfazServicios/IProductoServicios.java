package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.DTO.ProductoActualizarDto;
import com.example.demo.Modelos.DTO.ProductoCreateDto;

@Repository
public interface IProductoServicios {

	public List<Producto> listarProductos();

	public Optional<Producto> obtenerProducto(Integer id) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public Producto modificarProducto(Integer idE, Integer id, ProductoActualizarDto productoActualizarDto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
	
	public Producto agregarProducto(Integer idE, ProductoCreateDto productoCreateDto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
	
	public Producto eliminarProducto(Integer idE, Integer id)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
}
