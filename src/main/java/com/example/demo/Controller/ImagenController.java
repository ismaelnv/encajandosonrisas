package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.InterfazServicios.IImagenService;
import com.example.demo.Modelos.Imagen;

@RestController
@RequestMapping
public class ImagenController {

    @Autowired
    private IImagenService _serviceImagen;

    @PostMapping("/imagen")
	public void agreagrImagen(@RequestParam("file") MultipartFile file, Integer codigoProducto) {
		
        _serviceImagen.agregarImagen(file, codigoProducto);
	}

    @GetMapping("/imagen")
	public List<Imagen> listarImagenes(){
		
        List<Imagen> imagenes = _serviceImagen.listarImagenes();
		return imagenes;
	}
    
}
