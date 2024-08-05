package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.Modelos.TipoDeProducto;
import com.example.demo.Modelos.DTO.TipoDeProductoDtoActualizar;
import com.example.demo.Modelos.DTO.TipoDeProductoDtoCreate;

@Repository
public interface ITipoDeProductoService {

	public List<TipoDeProducto> listarTipoProductos();

	public Optional<TipoDeProducto> obtenerTipoDeProducto(Integer codigotProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public TipoDeProducto modificarTipoProducto(Integer codigoEmpleado, Integer codigotProducto, TipoDeProductoDtoActualizar tipoDeProductoDtoActualizar)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public TipoDeProducto agregarTipoProducto(Integer codigoEmpleado, TipoDeProductoDtoCreate tipoDeProductoDtoCreate)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
	
	public String eliminarTipoDeProducto(Integer codigoEmpleado, Integer codigotProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public TipoDeProducto obtenerProductosPorIdTProducto(Integer codigoTProducto, String orden)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
}
