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

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.EncajandoSonrisasExceptions.ExceptionDetails;
import com.example.demo.Interfaz.IImage;
import com.example.demo.InterfazServicios.IEmpleadoService;
import com.example.demo.InterfazServicios.IImagenService;
import com.example.demo.InterfazServicios.IProductoServicios;
import com.example.demo.Modelos.Empleado;
import com.example.demo.Modelos.Imagen;
import com.example.demo.Modelos.Producto;

@Service
public class ImagenService implements IImagenService {

    @Autowired 
    private IImage _repositoryImagen;

    @Autowired 
    private IProductoServicios _iProductoServicios;

    @Autowired IEmpleadoService _iEmpleadoService;

    private final String rutaGeneral = "src/main/resources/img/";

    @Override
    public List<Imagen> listarImagenes() {
        
        List<Imagen> imagenes = _repositoryImagen.findAll();
		return imagenes;
    }

    @Override
    public void agregarImagen(MultipartFile file,Integer codigoProducto, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
        

        if (codigoEmpleado == null || codigoEmpleado == 0) {

            throw new EncajandoSonrisasBadRequestExceptions("id del empleado no valido no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
        }

        Optional<Empleado> empleado = this._iEmpleadoService.obtenerEmpleado(codigoEmpleado);

        if (!empleado.isPresent()) {
            
            throw new EncajandoSonrisasNotFountExeptions("El empleado con el codigo "+codigoEmpleado+" no existe en la base de datos",
			new ExceptionDetails("No cuenta con los permisos suficientes", "Error"));
        }

        if (codigoProducto == 0 || codigoProducto == null) {

            throw new EncajandoSonrisasBadRequestExceptions("id del producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
        }

        Optional<Producto> productos = this._iProductoServicios.obtenerProducto(codigoProducto);

        if (!productos.isPresent()) {
            
            throw new EncajandoSonrisasNotFountExeptions("El producto con el codigo "+codigoProducto+" no existe en la base de datos",
			new ExceptionDetails("No se logro encontrar el producto en la base de datos", "Error"));
        }

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
    public Optional<Imagen> traerImagenPorId(Integer id) 
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
        
        if(id != 0 && id != null){

            Optional<Imagen> imagen = _repositoryImagen.findById(id); 

            if (!imagen.isPresent()) {

                throw new EncajandoSonrisasNotFountExeptions("La imagen con el codigo "+id+" No se encontro en la base de datos",
			    new ExceptionDetails("No se logro encontrar la imagen en la base de datos", "Error")); 
            }

            return imagen;
        }

        throw new EncajandoSonrisasBadRequestExceptions("id de la imagen no es valido", 
        new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
    }

    @Override
    public void actualizarImagenes(Integer id, Integer codigoEmpleado, Imagen imagen)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
        
        try {

            if (codigoEmpleado == null || codigoEmpleado == 0) {
                
                throw new EncajandoSonrisasBadRequestExceptions("id del empleado no es valido", 
                new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
            }

            Optional<Empleado> empleado = this._iEmpleadoService.obtenerEmpleado(codigoEmpleado);

            if (!empleado.isPresent()) {

                throw new EncajandoSonrisasNotFountExeptions("id "+codigoEmpleado+" del empleado no fue encontrando en la base de datos", 
                new ExceptionDetails("Su codigo de empleado no es valido para actualizar imagenes", "ERROR")); 
            }

            if (id == 0 || id == null) {

                throw new EncajandoSonrisasBadRequestExceptions("id de la imagen no valido", 
                new ExceptionDetails("Error Datos Invalidos", "ERROR"));
            }

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

            throw new EncajandoSonrisasNotFountExeptions("id "+id+" de la imagen no fue encontrado en la base de datos", 
            new ExceptionDetails("No se logro encontra la imagen", "ERROR")); 

        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarImagen(Integer id, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
        
        if (codigoEmpleado == 0 || codigoEmpleado == null){

            throw new EncajandoSonrisasBadRequestExceptions("id del empleado no es valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR"));             
        }

        Optional<Empleado> empleado = this._iEmpleadoService.obtenerEmpleado(codigoEmpleado);

        if (!empleado.isPresent()) {

            throw new EncajandoSonrisasNotFountExeptions("id "+codigoEmpleado+" del empleado no fue encontrando en la base de datos", 
            new ExceptionDetails("Su codigo de empleado no es valido para eliminar imagenes", "ERROR")); 
        }

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

            throw new EncajandoSonrisasNotFountExeptions("id "+id+" de la imagen no fue encontrado en la base de datos", 
            new ExceptionDetails("No se logro encontrar la imagen", "ERROR"));
        }

        throw new EncajandoSonrisasBadRequestExceptions("id de la imagen no valido", 
        new ExceptionDetails("Error Datos Invalidos", "ERROR"));
    }

    @Override
    public List<Imagen> agregarListaDeImagenes(List<MultipartFile> files, Integer codigoProducto, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {

        if (codigoEmpleado == null || codigoEmpleado == 0) {
                
            throw new EncajandoSonrisasBadRequestExceptions("id del empleado no es valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
        }

        Optional<Empleado> empleado = this._iEmpleadoService.obtenerEmpleado(codigoEmpleado);

        if (!empleado.isPresent()) {

            throw new EncajandoSonrisasNotFountExeptions("id "+codigoEmpleado+" del empleado no fue encontrando en la base de datos", 
            new ExceptionDetails("Su codigo de empleado no es valido para actualizar imagenes", "ERROR")); 
        }

        if (codigoProducto == 0 || codigoProducto == null) {

            throw new EncajandoSonrisasBadRequestExceptions("id del producto no valido", 
            new ExceptionDetails("Error Datos Invalidos", "ERROR")); 
        }

        Optional<Producto> productos = this._iProductoServicios.obtenerProducto(codigoProducto);

        if (!productos.isPresent()) {
            
            throw new EncajandoSonrisasNotFountExeptions("El producto con el codigo "+codigoProducto+" no existe en la base de datos",
			new ExceptionDetails("No se logro encontrar el producto en la base de datos", "Error"));
        }
        
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
