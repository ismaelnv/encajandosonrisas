package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.EncajandoSonrisasExceptions.ExceptionDetails;
import com.example.demo.Interfaz.IProducto;
import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.Imagen;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.DTO.ProductoActualizarDto;
import com.example.demo.Modelos.DTO.ProductoCreateDto;
import com.example.demo.mapper.AppMaper;

@Service
public class ProductoServicios implements IProductoServicios {

	@Autowired
	private IProducto _productoRepository;

	@Autowired
	private IEmpleadoService _empleadoService;

	@Autowired AppMaper _appMaper;

	private String rutaEstatica = "http://localhost:8080/imagenes/";

	@Override
	public List<Producto> listarProductos() {

		List<Producto> productos =	_productoRepository.findAll();

		for (Producto producto : productos) {
			
			for (Imagen imagen : producto.getImagenes()) {

				imagen.setNombre(rutaEstatica+imagen.getNombre());
			}
		}

		return productos;
	}

	@Override
	public Optional<Producto> obtenerProducto(Integer id)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{

		if(id == null  || id == 0){

			throw new EncajandoSonrisasBadRequestExceptions("id del producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
		}

		Optional<Producto> producto = _productoRepository.findById(id);

		if (!producto.isPresent()) {

			throw new EncajandoSonrisasNotFountExeptions("El producto con el codigo "+id+" no se encuentran en la base de datos",
			new ExceptionDetails("Producto no encontrado", "Error"));
		}

		for (Imagen imagen : producto.get().getImagenes()) {

			imagen.setNombre(rutaEstatica+imagen.getNombre());
		}

		return producto;
	}

	@Override
	public Producto modificarProducto(Integer idE, Integer id, ProductoActualizarDto productoActualizarDto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{

		if(idE == null || idE == 0){

			throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		if(id == 0 || id == null){

			throw new EncajandoSonrisasBadRequestExceptions("id del producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		Optional<Empleado> empelado = this._empleadoService.obtenerEmpleado(idE);

		if (empelado.isPresent()){

			Optional<Producto> producto = this.obtenerProducto(id);

			if (producto.isPresent()) {

				Producto productoActualizado = _appMaper.actualizarProductoDesdeDto(productoActualizarDto, producto.get());

				productoActualizado.setFecha_actu(LocalDate.now());
				return _productoRepository.save(productoActualizado);
			}

			throw new EncajandoSonrisasNotFountExeptions("El producto con el codigo "+id+" no se encuentran en la base de datos",
			new ExceptionDetails("Producto no encontrado", "Error"));
		}

		throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+idE+" no se encuentran en la base de datos",
		new ExceptionDetails("No cuenta con los permisos para actualizar productos", "Error"));
	}

	@Override
	public Producto agregarProducto(Integer idE, ProductoCreateDto productoCreateDto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{	

		if(idE == null || idE == 0){

			throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}
		
		Optional<Empleado> empleado = this._empleadoService.obtenerEmpleado(idE);

		if (!empleado.isPresent()) {
			
			throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+idE+" no se encuentran en la base de datos",
			new ExceptionDetails("No cuenta con los permisos para agregar productos", "Error"));
		}

		Producto producto = _appMaper.productoC(productoCreateDto);
		producto.setFecha_cre(LocalDate.now());
		producto.setEstado(true);

		Producto nuevoProducto = _productoRepository.save(producto);
		return nuevoProducto;
	}

	@Override
	public Producto eliminarProducto(Integer idE, Integer id)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{	

		if(idE == null || idE == 0){

			throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		if(id == 0 || id == null){

			throw new EncajandoSonrisasBadRequestExceptions("id del producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

		Optional<Empleado> empelado = this._empleadoService.obtenerEmpleado(idE);

		if (!empelado.isPresent()) {
			
			throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+idE+" no se encuentran en la base de datos",
			new ExceptionDetails("No cuenta con los permisos para eliminar productos", "Error"));
		}
		
	    Optional<Producto> optionalProducto = _productoRepository.findById(id);

	    if (optionalProducto.isPresent()) {

	        Producto producto = optionalProducto.get();
	        producto.setEstado(false);
	        return _productoRepository.save(producto);
	    }

	    throw new EncajandoSonrisasNotFountExeptions("El producto con el codigo "+id+" no se encuentran en la base de datos",
		new ExceptionDetails("Error al momento de eliminar el producto", "Error"));
	}
}
