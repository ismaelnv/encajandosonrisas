package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.InterfazServicios.IImagenService;
import com.example.demo.Modelos.Imagen;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "http://localhost:4200")
public class ImagenController {

    @Autowired
    private IImagenService _serviceImagen;

    @PostMapping
	public void agreagrImagen(@RequestParam("file") MultipartFile file, Integer codigoProducto) {
		
        _serviceImagen.agregarImagen(file, codigoProducto);
	}

    @GetMapping
	public List<Imagen> listarImagenes(){
		
        List<Imagen> imagenes = _serviceImagen.listarImagenes();
		return imagenes;
	}

    @GetMapping("/{id}")
    public Optional<Imagen> buscarImagenPorId(@PathVariable Integer id){

        return _serviceImagen.traerImagenPorId(id);
    }

    @PutMapping("/{id}")
    public void actuaizarImagen(@PathVariable Integer id, @RequestBody Imagen imagen){

        _serviceImagen.actualizarImagenes(id, imagen);
    }
    
    @DeleteMapping("/{id}")
    public void elimianrImagen(@PathVariable Integer id){

        _serviceImagen.elimianrImagen(id);
    }

    @PostMapping("/")
    public List<Imagen> agreagrImagenes(@RequestParam("file") List<MultipartFile> file, Integer codigoProducto){

        return  _serviceImagen.agreagrListaDeImagenes(file, codigoProducto);
    }
}
