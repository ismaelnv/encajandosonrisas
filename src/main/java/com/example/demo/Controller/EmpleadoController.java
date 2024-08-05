package com.example.demo.Controller;

import java.util.List;
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
import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.DTO.EmpleadoActualizarDto;
import com.example.demo.Modelos.DTO.EmpleadoCreateDto;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService _serviceEmpleado;

    @GetMapping
	public ResponseEntity<List<Empleado>> listarEmpleados(){
		
        List<Empleado> empleados = _serviceEmpleado.listarEmpleados();
		return ResponseEntity.ok(empleados);
	}
	
	@GetMapping("/{codigoEmpleado}")
	public ResponseEntity<Empleado> obtenerEmpleadoId(@PathVariable int codigoEmpleado)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{
		
        Optional<Empleado> empleado = _serviceEmpleado.obtenerEmpleado(codigoEmpleado);
		return ResponseEntity.ok(empleado.get());
	}
	
	@PutMapping("/{codigoEmpleado}")
	public ResponseEntity<ApiResponse<Empleado>> editarEmpleado(@PathVariable int codigoEmpleado, @RequestBody EmpleadoActualizarDto empleadoActualizarDto) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{
        
		Empleado empleadoModificado =  _serviceEmpleado.modificarEmpleado(codigoEmpleado, empleadoActualizarDto);
		ApiResponse<Empleado> empleadoMensaje = new ApiResponse<>("Empleado fue actualizado correctamente", empleadoModificado);
		return ResponseEntity.ok(empleadoMensaje);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Empleado>> crearEmpleado(@RequestBody EmpleadoCreateDto empleadoCreateDto) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{

        Empleado nuevoEmpleado = _serviceEmpleado.agregarEmpleado(empleadoCreateDto);
		ApiResponse<Empleado> empleadoMensaje = new ApiResponse<>(" El empleado fue creado exitosamente", nuevoEmpleado);
		return new ResponseEntity<>(empleadoMensaje, HttpStatus.CREATED);
	}

	@DeleteMapping("/{codigoEmpleado}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable int codigoEmpleado) 
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{

		String mensaje = _serviceEmpleado.eliminarEmpleado(codigoEmpleado);
		return ResponseEntity.ok(mensaje);
	}    
}
