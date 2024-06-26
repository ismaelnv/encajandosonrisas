package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Modelos.Imagen;

@Repository
public interface IImagenService {

    public List<Imagen> listarImagenes();
    public void agregarImagen(MultipartFile file, Integer codigoProducto);
    public Optional<Imagen> traerImagenPorId(Integer id); 
    public void actualizarImagenes(Integer id, Imagen imagen);
    public void elimianrImagen(Integer id);
    public List<Imagen> agreagrListaDeImagenes(List<MultipartFile> files, Integer codigoProducto);
}
