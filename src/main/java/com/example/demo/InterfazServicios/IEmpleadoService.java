package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.DTO.EmpleadoActualizarDto;
import com.example.demo.Modelos.DTO.EmpleadoCreateDto;

@Repository
public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

	public Optional<Empleado> obtenerEmpleado(Integer codigoEmpleado)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public Empleado modificarEmpleado(Integer codigoEmpleado, EmpleadoActualizarDto empleadoActualizarDto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public Empleado agregarEmpleado(EmpleadoCreateDto empleadoCreateDto)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

	public String eliminarEmpleado(Integer codigoEmpleado)
	throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;  
}
