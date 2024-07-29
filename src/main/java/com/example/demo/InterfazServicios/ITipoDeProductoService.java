package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.Modelos.TipoDeProducto;

@Repository
public interface ITipoDeProductoService {

	public List<TipoDeProducto> listarTipoProductos();

	public Optional<TipoDeProducto> obtenerTipoDeProducto(Integer codigotProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public TipoDeProducto modificarTipoProducto(Integer codigoEmpleado, Integer codigotProducto, TipoDeProducto tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public TipoDeProducto agregarTipoProducto(Integer codigoEmpleado, TipoDeProducto tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
	
	public void eliminarTipoDeProducto(Integer codigoEmpleado, Integer codigotProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public TipoDeProducto obtenerProductosPorIdTProducto(Integer codigoTProducto, String orden)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
}
