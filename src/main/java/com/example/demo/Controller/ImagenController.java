package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.InterfazServicios.IImagenService;
import com.example.demo.Modelos.Imagen;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "http://localhost:4200")
public class ImagenController {

    @Autowired
    private IImagenService _serviceImagen;

    @PostMapping
	public ResponseEntity<Void> agreagrImagen(@RequestParam("file") MultipartFile file, Integer codigoProducto, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {
		
        _serviceImagen.agregarImagen(file, codigoProducto,codigoEmpleado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
	}

    @GetMapping
	public ResponseEntity<List<Imagen>> listarImagenes(){
		
        List<Imagen> imagenes = _serviceImagen.listarImagenes();
		return ResponseEntity.ok(imagenes);
	}

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Imagen>> buscarImagenPorId(@PathVariable Integer id)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions
    {

        Optional<Imagen> imagen = _serviceImagen.traerImagenPorId(id);
        return ResponseEntity.ok(imagen);
    }

    @PutMapping("/{id}/{codigoEmpleado}")
    public ResponseEntity<Void> actuaizarImagen(@PathVariable Integer id, @RequestBody Imagen imagen, @PathVariable Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{

        _serviceImagen.actualizarImagenes(id, codigoEmpleado, imagen);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @DeleteMapping("/{id}/{codigoEmpleado}")
    public ResponseEntity<Void> elimianrImagen(@PathVariable Integer id, @PathVariable Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{

        _serviceImagen.eliminarImagen(id, codigoEmpleado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/lisImagenes")
    public ResponseEntity<List<Imagen>> agreagrImagenes(@RequestParam("file") List<MultipartFile> file, Integer codigoProducto, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions{

        List<Imagen> imagens = _serviceImagen.agregarListaDeImagenes(file, codigoProducto, codigoEmpleado);
        return new ResponseEntity<>(imagens, HttpStatus.CREATED);
    }
}
