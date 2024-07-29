package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.IEmpleado;
import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.Modelos.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleado _repositoryEmpleado;

    @Override
    public List<Empleado> listarEmpleados() {
       
        List<Empleado> empleados = _repositoryEmpleado.findAll();
		return empleados;
    }

    @Override
    public Optional<Empleado> obtenerEmpleado(int codigoEmpleado) {
        
        if( codigoEmpleado == 0){

			return null;
		}
		
		return _repositoryEmpleado.findById(codigoEmpleado);
    }

    @Override
    public Empleado modificarEmpleado(int codigoEmpleado, Empleado empleado) {
      
        Optional<Empleado> emple = this.obtenerEmpleado(codigoEmpleado);

		if (emple.isPresent()) {  

			empleado.setFechaActualizacion(LocalDate.now());
			return _repositoryEmpleado.save(empleado);
		}

		return null;
    }

    @Override
    public Empleado agregarEmpleado(Empleado empleado) {
        
        if (empleado == null) {

			return null;
		}

        empleado.setFechaCreacion(LocalDate.now());
        empleado.setFechaActualizacion(LocalDate.now());
		return _repositoryEmpleado.save(empleado);
    }

    @Override
    public void eliminarEmpleado(int codigoEmpleado) {
        
        _repositoryEmpleado.deleteById(codigoEmpleado);
    }
}
