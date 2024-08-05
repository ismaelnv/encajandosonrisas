package com.example.demo.Modelos.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.Producto;
import com.example.demo.Modelos.TipoDeProducto;

@Mapper(componentModel = "spring")
public interface AppMaper {

    // Metodos de mapeo para Modelo Tipo de Productos

    @Mappings({

        @Mapping(target = "codigoTP", ignore = true),
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaActualizacion", ignore = true),
        @Mapping(target = "productos", ignore = true),
        @Mapping(target = "estado", ignore = true)  
    })
    TipoDeProducto tipoDeProductoC(TipoDeProductoDtoCreate tipoDeProductoDtoCreate);

    @Mappings({

        @Mapping(target = "codigoTP", ignore = true),
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaActualizacion", ignore = true),
        @Mapping(target = "productos", ignore = true)  
    })
    TipoDeProducto actualizarTipoDeProductoDesdeDto(TipoDeProductoDtoActualizar dto, @MappingTarget TipoDeProducto tipoDeProducto);

    //Metodos de mapeo para el Modelo Producto

    @Mappings({
        
        @Mapping(target = "cod_pro", ignore = true),
        @Mapping(target = "fecha_cre", ignore = true),
        @Mapping(target = "fecha_actu", ignore = true),
        @Mapping(target = "estado", ignore = true),
        @Mapping(target = "imagen_pro", ignore = true),
        @Mapping(target = "tipoDeProducto", ignore = true),
        @Mapping(target = "imagenes", ignore = true),
    })
    Producto productoC(ProductoCreateDto productoCreateDto);
    
    @Mappings({
        
        @Mapping(target = "cod_pro", ignore = true),
        @Mapping(target = "fecha_cre", ignore = true),
        @Mapping(target = "fecha_actu", ignore = true),
        @Mapping(target = "imagen_pro", ignore = true),
        @Mapping(target = "tipoDeProducto", ignore = true),
        @Mapping(target = "imagenes", ignore = true),
    })
    Producto actualizarProductoDesdeDto(ProductoActualizarDto dto, @MappingTarget Producto producto);

    //Metodos de mapeo para el Modelo Empleado
    @Mappings({
        
        @Mapping(target = "codigoEmpleado", ignore = true),
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaActualizacion", ignore = true),
        @Mapping(target = "codigoPersona", ignore = true),
    })
    Empleado empleadoC(EmpleadoCreateDto empleadoCreateDto);

    @Mappings({
        
        @Mapping(target = "codigoEmpleado", ignore = true),
        @Mapping(target = "codigoPersona", ignore = true),
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaActualizacion", ignore = true),
       
    })
    Empleado actualizarEmpleadoDesdeDto(EmpleadoActualizarDto dto, @MappingTarget Empleado empleado);
}
