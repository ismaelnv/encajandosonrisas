package com.example.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.EncajandoSonrisasResponse.ApiResponse;
import com.example.demo.InterfazServicios.ITipoDeProductoService;
import com.example.demo.Modelos.TipoDeProducto;
import com.example.demo.Modelos.DTO.TipoDeProductoDtoActualizar;
import com.example.demo.Modelos.DTO.TipoDeProductoDtoCreate;

@RestController
@RequestMapping("/tipo_productos")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoDeProductoController {

    @Autowired
    private ITipoDeProductoService _serviceTipoProducto;

    @GetMapping
	public ResponseEntity<List<TipoDeProducto>> ObtenerTipoProductos(){

		return ResponseEntity.ok(_serviceTipoProducto.listarTipoProductos());
	}

    @PostMapping("/{idEmpleado}")
	public ResponseEntity<ApiResponse<TipoDeProducto>> agregarTProducto(@PathVariable Integer idEmpleado, @RequestBody TipoDeProductoDtoCreate tipoDeProductoDtoCreate)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
	{
    	
		TipoDeProducto nuevoTProducto = _serviceTipoProducto.agregarTipoProducto(idEmpleado, tipoDeProductoDtoCreate);
		ApiResponse<TipoDeProducto> tProductoMensaje = new ApiResponse<>("Tipo de producto fue creado exitosamente", nuevoTProducto);
		return new ResponseEntity<>(tProductoMensaje, HttpStatus.CREATED);
	}

    @GetMapping("/{tProducto}")
	public ResponseEntity<Optional<TipoDeProducto>> obtenerTProductoId(@PathVariable Integer tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		Optional<TipoDeProducto> producto = _serviceTipoProducto.obtenerTipoDeProducto(tProducto);
		return ResponseEntity.ok(producto);
	}

    @PutMapping("/{idEmpleado}/{codigoTProducto}")
	public ResponseEntity<ApiResponse<TipoDeProducto>> editarTProducto(@PathVariable Integer idEmpleado,
	@PathVariable Integer codigoTProducto, @RequestBody TipoDeProductoDtoActualizar tipoProductoDtoActualizar) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		TipoDeProducto tipoProducto =  _serviceTipoProducto.modificarTipoProducto(idEmpleado, codigoTProducto, tipoProductoDtoActualizar);
		ApiResponse<TipoDeProducto> tProductoMensaje = new ApiResponse<>("El tipo de producto fue actualizado exitosamente", tipoProducto);
		return ResponseEntity.ok(tProductoMensaje);
	}

	@DeleteMapping("/{idEmpleado}/{tProducto}")
	public ResponseEntity<String> elimianrTipoProducto(@PathVariable Integer idEmpleado, @PathVariable Integer tProducto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{

		String mensaje = _serviceTipoProducto.eliminarTipoDeProducto(idEmpleado, tProducto);
		return ResponseEntity.ok(mensaje);
	}

	@PostMapping("/{codigoTipoProducto}/productos")
	public ResponseEntity<TipoDeProducto> obtenerProductosPorTProductoId(@PathVariable int codigoTipoProducto, @RequestBody Map<Object,String> orden)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
	{
		
		String orde = orden.get("orden");
		TipoDeProducto tProducto = _serviceTipoProducto.obtenerProductosPorIdTProducto(codigoTipoProducto, orde);
		return ResponseEntity.ok(tProducto);
	}
}
