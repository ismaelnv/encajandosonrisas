package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Empleado;

@Repository
public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();
	public Optional<Empleado> obtenerEmpleado(int codigoEmpleado);
	public Empleado modificarEmpleado(int codigoEmpleado, Empleado empleado);
	public Empleado agregarEmpleado(Empleado empleado);
	public void eliminarEmpleado(int codigoEmpleado);  
}
