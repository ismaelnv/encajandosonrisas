package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.InterfazServicios.ITipoDeProductoService;
import com.example.demo.Modelos.TipoDeProducto;

@RestController
@RequestMapping
public class TipoDeProductoController {

    @Autowired
    private ITipoDeProductoService _serviceTipoProducto;
    
    @GetMapping("/tipo_productos")
	public List<TipoDeProducto> ObtenerTipoProductos(){

		return _serviceTipoProducto.listarTipoProductos();
	
	}

    @PostMapping("/tipo_productos")
	public TipoDeProducto agregarTProducto(@RequestBody TipoDeProducto tProducto) {
    	
		return _serviceTipoProducto.agregarTipoProducto(tProducto);
	}

    @GetMapping("/tipo_productos/{tProducto}")
	public Optional<TipoDeProducto> obtenerTProductoId(@PathVariable int tProducto){
		
		return _serviceTipoProducto.obtenerTipoDeProducto(tProducto);
	}

    @PutMapping("/tipo_productos/{codigoTProducto}")
	public TipoDeProducto editarTProducto(@PathVariable int codigoTProducto, @RequestBody TipoDeProducto tDProducto) {
		
		TipoDeProducto tipoProducto =  _serviceTipoProducto.modificarTipoProducto(codigoTProducto, tDProducto);
		return tipoProducto;
	}

    @GetMapping("/tipo_productos/{tProducto}/productos")
	public TipoDeProducto obtenerProductosPorTProductoId(@PathVariable int tProducto){
		
		return _serviceTipoProducto.obtenerProductosPorIdTProducto(tProducto);
	}

	@DeleteMapping("/tipo_productos/{tProducto}")
	public void elimianrTipoProducto(@PathVariable int tProducto){

		_serviceTipoProducto.eliminarTipoDeProducto(tProducto);
	}
}
