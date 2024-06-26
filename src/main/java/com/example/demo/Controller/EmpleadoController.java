package com.example.demo.Controller;

import java.util.List;
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

import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.Modelos.Empleado;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService _serviceEmpleado;

    @GetMapping("/empleados")
	public List<Empleado> listarEmpleados(){
		
        List<Empleado> empleados = _serviceEmpleado.listarEmpleados();
		return empleados;
	}
	
	@GetMapping("/empleados/{codigoEmpleado}")
	public Optional<Empleado> obtenerEmpleadoId(@PathVariable int codigoEmpleado){
		
        return _serviceEmpleado.obtenerEmpleado(codigoEmpleado);
	}
	
	@PutMapping("/empleados/{codigoEmpleado}")
	public Empleado editarEmpleado(@PathVariable int codigoEmpleado, @RequestBody Empleado empleado) {
        
		Empleado empleadoModificado =  _serviceEmpleado.modificarEmpleado(codigoEmpleado, empleado);
		return empleadoModificado;
	}
	
	@PostMapping("/empleados")
	public Empleado crearEmpleado(@RequestBody Empleado empleado) {
		
        return _serviceEmpleado.agregarEmpleado(empleado);
	}

	@DeleteMapping("/empleado/{codigoEmpleado}")
	public void eliminarEmpleado(@PathVariable int codigoEmpleado) {

		_serviceEmpleado.eliminarEmpleado(codigoEmpleado);
	}    
}
