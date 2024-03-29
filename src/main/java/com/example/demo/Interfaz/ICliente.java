package com.example.demo.Interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Cliente;

@Repository
public interface ICliente extends JpaRepository<Cliente, Integer>{

}
