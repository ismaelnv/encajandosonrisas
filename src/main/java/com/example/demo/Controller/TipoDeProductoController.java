package com.example.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.InterfazServicios.ITipoDeProductoService;
import com.example.demo.Modelos.TipoDeProducto;

@RestController
@RequestMapping("/tipo_productos")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoDeProductoController {

    @Autowired
    private ITipoDeProductoService _serviceTipoProducto;
    
    @GetMapping
	public List<TipoDeProducto> ObtenerTipoProductos(){

		return _serviceTipoProducto.listarTipoProductos();
	}

    @PostMapping("/{idEmpleado}")
	public TipoDeProducto agregarTProducto(@PathVariable Integer idEmpleado, @RequestBody TipoDeProducto tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{
    	
		return _serviceTipoProducto.agregarTipoProducto(idEmpleado, tProducto);
	}

    @GetMapping("/{tProducto}")
	public Optional<TipoDeProducto> obtenerTProductoId(@PathVariable Integer tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		return _serviceTipoProducto.obtenerTipoDeProducto(tProducto);
	}

    @PutMapping("/{idEmpleado}/{codigoTProducto}")
	public TipoDeProducto editarTProducto(@PathVariable Integer idEmpleado,
	@PathVariable Integer codigoTProducto, @RequestBody TipoDeProducto tDProducto) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		TipoDeProducto tipoProducto =  _serviceTipoProducto.modificarTipoProducto(idEmpleado, codigoTProducto, tDProducto);
		return tipoProducto;
	}

	@DeleteMapping("/{idEmpleado}/{tProducto}")
	public void elimianrTipoProducto(@PathVariable Integer idEmpleado, @PathVariable Integer tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{

		_serviceTipoProducto.eliminarTipoDeProducto(idEmpleado, tProducto);
	}

	@PostMapping("/{tProducto}/productos")
	public TipoDeProducto obtenerProductosPorTProductoId(@PathVariable int tProducto, @RequestBody Map<Object,String> orden)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		String orde = orden.get("orden");
		return _serviceTipoProducto.obtenerProductosPorIdTProducto(tProducto, orde);
	}
}
