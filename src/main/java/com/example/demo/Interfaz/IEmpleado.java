package com.example.demo.Interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Empleado;

@Repository
public interface IEmpleado extends JpaRepository<Empleado, Integer>{
    
}
