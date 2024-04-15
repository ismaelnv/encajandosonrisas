package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Interfaz.ITipoDeProducto;
import com.example.demo.InterfazServicios.ITipoDeProductoService;
import com.example.demo.Modelos.TipoDeProducto;

@Service
public class TipoDeProductoService implements ITipoDeProductoService{

	@Autowired
	private ITipoDeProducto _repositoryTDProducto;

	@Override
	public List<TipoDeProducto> listarTipoProductos() {

		List<TipoDeProducto> tProductos = _repositoryTDProducto.findAll();
		return tProductos;
	}

	@Override
	public Optional<TipoDeProducto> obtenerTipoDeProducto(int codigotProducto) {
		
		if( codigotProducto == 0){

			return null;
		}
		
		return _repositoryTDProducto.findById(codigotProducto);
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
	public TipoDeProducto obtenerProductosPorIdTProducto(int codigoTProducto) {
		
		if (codigoTProducto == 0) {
			
			return null;
		}

		return _repositoryTDProducto.obtenerProductosPorIdTipoProducto(codigoTProducto);
	}
	 
}
