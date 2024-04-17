package com.example.demo.InterfazServicios;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Modelos.Imagen;

@Repository
public interface IImagenService {

    public List<Imagen> listarImagenes();
    public void agregarImagen(MultipartFile file, Integer codigoProducto);
}
