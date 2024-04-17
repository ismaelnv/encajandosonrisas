package com.example.demo.Servicios;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Interfaz.IImage;
import com.example.demo.InterfazServicios.IImagenService;
import com.example.demo.Modelos.Imagen;

@Service
public class ImagenService implements IImagenService {

    @Autowired 
    private IImage _repositoryImagen;

    private final String rutaGeneral = "src/main/resources/img/";

    @Override
    public List<Imagen> listarImagenes() {
        
        List<Imagen> imagenes = _repositoryImagen.findAll();
		return imagenes;
    }

    @Override
    public void agregarImagen(MultipartFile file,Integer codigoProducto){
        
        String nombreImagen = file.getOriginalFilename();
        String rutaArchivo = rutaGeneral+nombreImagen;
        Path carpetaImagenes = Paths.get(rutaArchivo).getParent();

        try{

            if (!Files.exists(carpetaImagenes)) {

               Files.createDirectories(carpetaImagenes);
            }

            byte[] bytesArchivo = file.getBytes();
            Path rutaCompleta = Paths.get(rutaArchivo);
            Files.write(rutaCompleta, bytesArchivo);

            Imagen imagen = new Imagen();
            imagen.setNombre(nombreImagen);
            imagen.setRuta(rutaArchivo);
            imagen.setCodigoProducto(codigoProducto);

            _repositoryImagen.save(imagen);

        }catch(IOException e){
            
            e.printStackTrace();

            System.out.println(e.getMessage());            
        }
    } 
}
