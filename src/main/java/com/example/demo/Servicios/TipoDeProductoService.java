package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.EncajandoSonrisasExceptions.ExceptionDetails;
import com.example.demo.Interfaz.ITipoDeProducto;
import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.InterfazServicios.ITipoDeProductoService;
import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.Imagen;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.TipoDeProducto;

@Service
public class TipoDeProductoService implements ITipoDeProductoService{

	@Autowired
	private ITipoDeProducto _repositoryTDProducto;

	@Autowired
	private IEmpleadoService _empleadoService;

	private String rutaEstatica = "http://localhost:8080/imagenes/";

	@Override
	public List<TipoDeProducto> listarTipoProductos() {

		List<TipoDeProducto> tProductos = _repositoryTDProducto.findAll();
		return tProductos;
	}

	@Override
	public Optional<TipoDeProducto> obtenerTipoDeProducto(Integer codigotProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{

		if(codigotProducto == 0 || codigotProducto == null){

			throw new EncajandoSonrisasBadRequestExceptions("id del tipo de producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}
	
	   Optional<TipoDeProducto>	tipoDeProducto = _repositoryTDProducto.findById(codigotProducto);

	    if (tipoDeProducto.isPresent()) {

			for (Producto producto :  tipoDeProducto.get().getProductos()){
				for (Imagen imagen : producto.getImagenes()) {
	
					imagen.setNombre(rutaEstatica+imagen.getNombre());	
				}
			}

			return tipoDeProducto;
		}

		throw new EncajandoSonrisasNotFountExeptions("No se encontro el tipo de producto con el codigo "+codigotProducto+" en la base de datos", 
        new ExceptionDetails("Tipo de poducto no encontrado", "ERROR"));
	}

	@Override
	public TipoDeProducto modificarTipoProducto(Integer codigoEmpleado, Integer codigotProducto, TipoDeProducto tProducto) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{

		if (codigoEmpleado == null || codigoEmpleado == 0) {
			
			throw new EncajandoSonrisasBadRequestExceptions("id empleado  no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		if (codigotProducto == null || codigotProducto == 0) {
			
			throw new EncajandoSonrisasBadRequestExceptions("id tipo de producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		Optional<Empleado> empleado = this._empleadoService.obtenerEmpleado(codigoEmpleado);
		
		if (!empleado.isPresent()) {

			throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+codigoEmpleado+" no se encuentra en la base de datos",
			new ExceptionDetails("No cuenta con los permisos para actualizar productos", "Error"));	
		}
		
		Optional<TipoDeProducto> tProduc = this.obtenerTipoDeProducto(codigotProducto);

		if (tProduc.isPresent()) {

			tProducto.setFechaActualizacion(LocalDate.now());
			return _repositoryTDProducto.save(tProducto);
		}

		throw new EncajandoSonrisasNotFountExeptions("El tipo de producto con el codigo "+codigotProducto+" no se encuentran en la base de datos",
		new ExceptionDetails("No se pudo actualizar el tipo de Producto", "Error"));
	}

	@Override
	public TipoDeProducto agregarTipoProducto(Integer codigoEmpleado, TipoDeProducto tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{	

		if (codigoEmpleado == null || codigoEmpleado == 0) {
			
			throw new EncajandoSonrisasBadRequestExceptions("id empleado  no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		Optional<Empleado> empleado = this._empleadoService.obtenerEmpleado(codigoEmpleado);

		if (!empleado.isPresent()) {

			throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+codigoEmpleado+" no se encuentra en la base de datos",
			new ExceptionDetails("No cuenta con los permisos para agregar un Tipo de Producto", "Error"));
		}

		TipoDeProducto tipoDeProductoNuevo = _repositoryTDProducto.save(tProducto);
		return tipoDeProductoNuevo;
	}

	@Override
	public void eliminarTipoDeProducto(Integer codigoEmpleado, Integer codigotProducto) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		if (codigoEmpleado == 0 || codigoEmpleado == null) {
			
			throw new EncajandoSonrisasBadRequestExceptions("id empleado  no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		if (codigotProducto == null || codigotProducto == 0) {
			
			throw new EncajandoSonrisasBadRequestExceptions("Id Tipo de Producto  no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		Optional<Empleado> empleado = this._empleadoService.obtenerEmpleado(codigoEmpleado);

		if (!empleado.isPresent()) {

			throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+codigoEmpleado+" no se encuentra en la base de datos",
			new ExceptionDetails("No cuenta con los permisos para agregar un Tipo de Producto", "Error"));
		}

		Optional<TipoDeProducto> tProducto = this.obtenerTipoDeProducto(codigotProducto);

		if (!tProducto.isPresent()) {

			throw new EncajandoSonrisasNotFountExeptions("No se encontro en la base de datos el Tipo de Producto",
			new ExceptionDetails("No se pudo eliminar el tipo de producto", "Error"));	
		}

		_repositoryTDProducto.deleteById(codigotProducto);
	}

	@Override
	public TipoDeProducto obtenerProductosPorIdTProducto(Integer codigoTProducto, String orden)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{
		
		if (codigoTProducto == 0 || codigoTProducto == null) {
			
			throw new EncajandoSonrisasBadRequestExceptions("Id Tipo de Producto  no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		TipoDeProducto tipoDeProducto =  _repositoryTDProducto.obtenerProductosPorIdTipoProducto(codigoTProducto);	
		
		if (tipoDeProducto != null) {

			List<Producto> productos =  tipoDeProducto.getProductos();

			if("Precio".equals(orden)){

				productos.sort(Comparator.comparingDouble(Producto::getPrecio));

			}else if("Nombre producto".equals(orden)){

				productos.sort(Comparator.comparing(Producto::getNombre_pro));
			}

			for (Producto producto :  productos){
				for (Imagen imagen : producto.getImagenes()) {

					imagen.setNombre(rutaEstatica+imagen.getNombre());	
				}
			}

			return tipoDeProducto;
		}
		

		throw new EncajandoSonrisasNotFountExeptions("No se encontro en la base de datos el Tipo de Producto",
		new ExceptionDetails("Tipo de Producto no encontrado", "Error"));
	}
}
