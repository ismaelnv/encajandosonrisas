package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Interfaz.ITipoDeProducto;
import com.example.demo.InterfazServicios.ITipoDeProductoService;
import com.example.demo.Modelos.Imagen;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.TipoDeProducto;

@Service
public class TipoDeProductoService implements ITipoDeProductoService{

	@Autowired
	private ITipoDeProducto _repositoryTDProducto;

	private String rutaEstatica = "http://localhost:8080/imagenes/";

	@Override
	public List<TipoDeProducto> listarTipoProductos() {

		List<TipoDeProducto> tProductos = _repositoryTDProducto.findAll();
		return tProductos;
	}

	@Override
	public Optional<TipoDeProducto> obtenerTipoDeProducto(int codigotProducto) {

		if(codigotProducto == 0){

			return null;
		}
	
	   Optional<TipoDeProducto>	tipoDeProducto = _repositoryTDProducto.findById(codigotProducto);

	    for (Producto producto :  tipoDeProducto.get().getProductos()){
			for (Imagen imagen : producto.getImagenes()) {

				imagen.setNombre(rutaEstatica+imagen.getNombre());	
			}
	    }

		return tipoDeProducto;
	}

	@Override
	public TipoDeProducto modificarTipoProducto(int codigotProducto, TipoDeProducto tProducto) {
		
		Optional<TipoDeProducto> tProduc = this.obtenerTipoDeProducto(codigotProducto);

		if (tProduc.isPresent()) {

			tProducto.setFechaActualizacion(LocalDate.now());
			return _repositoryTDProducto.save(tProducto);
		}

		return null;
	}

	@Override
	public TipoDeProducto agregarTipoProducto(TipoDeProducto tProducto) {
		
		
		if (tProducto == null) {

			return null;
		}

		return _repositoryTDProducto.save(tProducto);
	}

	@Override
	public void eliminarTipoDeProducto(int codigotProducto) {
		
		_repositoryTDProducto.deleteById(codigotProducto);
	}

	@Override
	public List<Producto> traerlistaDeProductosPorPrecio(TipoDeProducto tProducto) {
		
		List<Producto> productos = tProducto.getProductos();
	
		if (productos != null) {

			productos.sort(Comparator.comparing(Producto::getPrecio));
		}

      	return productos;
	}

	@Override
	public TipoDeProducto obtenerProductosPorIdTProducto(int codigoTProducto, String orden) {
		
		if (codigoTProducto == 0) {
			
			return null;
		}

		TipoDeProducto tipoDeProducto =  _repositoryTDProducto.obtenerProductosPorIdTipoProducto(codigoTProducto);

		for (Producto producto :  tipoDeProducto.getProductos()){
			for (Imagen imagen : producto.getImagenes()) {

				imagen.setNombre(rutaEstatica+imagen.getNombre());	
			}
	    }

		return tipoDeProducto;
	}
	 
}
