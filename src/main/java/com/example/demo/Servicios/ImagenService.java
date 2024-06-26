package com.example.demo.Servicios;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Imagen> traerImagenPorId(Integer id) {
        
        if(id != 0 && id != null){

           return _repositoryImagen.findById(id); 
        }

        return Optional.empty();
    }

    @Override
    public void actualizarImagenes(Integer id, Imagen imagen){
        
        try {

            Optional<Imagen> imagenObtenida = traerImagenPorId(id);

            //Validar si la imagen fue obtenida 
            if (imagenObtenida.isPresent()) {

                imagenObtenida.get().setCodigoImagen(id);

                imagenObtenida.get().setCodigoProducto(imagen.getCodigoProducto());

                String rutaAntigua = imagenObtenida.get().getRuta();
                String rutaNueva  = rutaGeneral+imagen.getNombre(); 
                
                Path archivoAntiguo = Paths.get(rutaAntigua);
                Path archivoNuevo = Paths.get(rutaNueva);

                if (Files.exists(archivoAntiguo)) {

                    Files.move(archivoAntiguo, archivoNuevo, StandardCopyOption.REPLACE_EXISTING);
                        
                    imagenObtenida.get().setRuta(rutaNueva); 
                    imagenObtenida.get().setNombre(imagen.getNombre());
                    _repositoryImagen.save(imagenObtenida.get());
                }   
            }

        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }

    @Override
    public void elimianrImagen(Integer id) {
        
        if(id != 0 && id != null){

            Optional<Imagen> imagen = traerImagenPorId(id);

            if (imagen.isPresent()) {

                String ruta = imagen.get().getRuta();
                Path archivo = Paths.get(ruta);
    
                try {
    
                    if (Files.exists(archivo)) {
    
                        Files.deleteIfExists(archivo);

                        _repositoryImagen.deleteById(imagen.get().getCodigoImagen());
                    }
       
                } catch (IOException e) {
    
                    e.printStackTrace();
                }
              
            }
        }
    }

    @Override
    public List<Imagen> agreagrListaDeImagenes(List<MultipartFile> files, Integer codigoProducto) {
        
        List<Integer> idImagenesNuevas = new ArrayList<>();

        for (MultipartFile file : files){

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
    
                Imagen imagenNueva = _repositoryImagen.save(imagen);
                idImagenesNuevas.add(imagenNueva.getCodigoImagen());
    
            }catch(IOException e){
                
                e.printStackTrace();
    
                System.out.println(e.getMessage());            
            } 
        }

        return _repositoryImagen.findAllById(idImagenesNuevas);
    } 
    
}
