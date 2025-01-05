package com.example.demo.Servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.EncajandoSonrisasExceptions.ExceptionDetails;
import com.example.demo.Interfaz.IEmpleado;
import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.DTO.EmpleadoActualizarDto;
import com.example.demo.Modelos.DTO.EmpleadoCreateDto;
import com.example.demo.mapper.AppMaper;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleado _repositoryEmpleado;

    @Autowired
    private AppMaper _appMaper;

    @Override
    public List<Empleado> listarEmpleados() {
       
        List<Empleado> empleados = _repositoryEmpleado.findAll();
		return empleados;
    }

    @Override
    public Optional<Empleado> obtenerEmpleado(Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions 
    {
        
        if( codigoEmpleado == 0 || codigoEmpleado == null){

			throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}
		
	    Optional<Empleado> empleado = _repositoryEmpleado.findById(codigoEmpleado);

        if (!empleado.isPresent()) {

            throw new EncajandoSonrisasNotFountExeptions("no se encontro el empleado con el id "+codigoEmpleado, 
            new ExceptionDetails("No se pudo encontrar el empleado", "ERROR"));
        }

        return empleado;
    }

    @Override
    public Empleado modificarEmpleado(Integer codigoEmpleado, EmpleadoActualizarDto empleadoActualizarDto) 
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{

        if (codigoEmpleado == null || codigoEmpleado == 0) {

            throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
        }

        Optional<Empleado> empleadoEncontrado = this.obtenerEmpleado(codigoEmpleado);

        if (!empleadoEncontrado.isPresent()) {
            
            throw new EncajandoSonrisasNotFountExeptions("no se encontro el empleado con el id "+codigoEmpleado, 
            new ExceptionDetails("No se pudo encontrar el empleado", "ERROR"));
        }

        Empleado empleado = _appMaper.actualizarEmpleadoDesdeDto(empleadoActualizarDto, empleadoEncontrado.get());
	    empleado.setFechaActualizacion(LocalDate.now());
        
		return _repositoryEmpleado.save(empleado);
    }

    @Override
    public Empleado agregarEmpleado(EmpleadoCreateDto empleadoCreateDto) 
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
        
        if (empleadoCreateDto == null) {

			throw new EncajandoSonrisasBadRequestExceptions("El empleado es nulo", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
		}

        Empleado empleado = _appMaper.empleadoC(empleadoCreateDto);
        empleado.setFechaCreacion(LocalDate.now());
     
		return _repositoryEmpleado.save(empleado);
    }

    @Override
    public String eliminarEmpleado(Integer codigoEmpleado) 
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
        if (codigoEmpleado == null || codigoEmpleado == 0) {
            
            throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));
        }   

        Optional<Empleado> empleado = this.obtenerEmpleado(codigoEmpleado);

        if (empleado.isPresent()) {

            throw new EncajandoSonrisasNotFountExeptions("no se encontro el empleado que desea eliminar con el id "+codigoEmpleado, 
            new ExceptionDetails("No se pudo eliminar el empleado", "ERROR"));
        }

        _repositoryEmpleado.deleteById(codigoEmpleado);
        return "El empleado ha sido eliminado exitosamente.";
    }
}
