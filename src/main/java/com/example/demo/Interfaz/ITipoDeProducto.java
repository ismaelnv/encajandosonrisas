package com.example.demo.Interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.TipoDeProducto;

@Repository
public interface ITipoDeProducto extends JpaRepository<TipoDeProducto, Integer>{

    //Anotacion encargada de resivir el id codigoTProducto y traer una lista de productos 
    @Query("SELECT tp FROM TipoDeProducto tp JOIN FETCH tp.productos WHERE tp.codigoTP = :codigoTProducto")
    TipoDeProducto obtenerProductosPorIdTipoProducto(@Param("codigoTProducto") Integer codigoTProducto);

    // @Query("SELECT t FROM TipoDeProducto t")
    // List<TipoDeProducto> findAllTProductos();
    
}
