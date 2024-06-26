package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Interfaz.IProducto;
import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Imagen;
import com.example.demo.Modelos.Producto;

@Service
public class ProductoServicios implements IProductoServicios {

	@Autowired
	private IProducto _productoRepository;

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
	public Optional<Producto> obtenerProducto(int id) {

		Optional<Producto> producto = _productoRepository.findById(id);

		for (Imagen imagen : producto.get().getImagenes()) {

			imagen.setNombre(rutaEstatica+imagen.getNombre());
		}

		return producto;
	}

	@Override
	public Producto modificarProducto(int id, Producto p) {

		Optional<Producto> producto = this.obtenerProducto(id);
		if (producto.isPresent()) {
			p.setFecha_actu(LocalDate.now());
			return _productoRepository.save(p);
		}
		return null;
		
	}

	@Override
	public Producto agregarProducto(Producto p) {

		Producto producto = _productoRepository.save(p);
		return producto;
	}

	@Override
	public Producto eliminarProducto(int id) {
		
	    Optional<Producto> optionalProducto = _productoRepository.findById(id);
	    if (optionalProducto.isPresent()) {
	        Producto producto = optionalProducto.get();
	        producto.setEstado(false);
	        return _productoRepository.save(producto);
	    }
	    return null;
	}

	@Override
	public void eliminarProductoDelSistema(Integer id) {
		
		if(id != 0 && id != null){

			_productoRepository.deleteById(id);
		}
	}

}
