package com.example.demo.Interfaz;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Producto;

@Repository
public interface IProducto extends JpaRepository<Producto, Integer>{

            
   
}
