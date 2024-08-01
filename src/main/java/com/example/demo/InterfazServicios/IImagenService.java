package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasBadRequestExceptions;
import com.example.demo.EncajandoSonrisasExceptions.EncajandoSonrisasNotFountExeptions;
import com.example.demo.Modelos.Imagen;

@Repository
public interface IImagenService {

    public List<Imagen> listarImagenes();

    public void agregarImagen(MultipartFile file, Integer codigoProducto, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

    public Optional<Imagen> traerImagenPorId(Integer id)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions; 

    public void actualizarImagenes(Integer id, Integer codigoEmpelado, Imagen imagen)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

    public void eliminarImagen(Integer id, Integer codigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;

    public List<Imagen> agregarListaDeImagenes(List<MultipartFile> files, Integer codigoProducto, Integer CodigoEmpleado)
    throws EncajandoSonrisasBadRequestExceptions, 
	EncajandoSonrisasNotFountExeptions;
}
