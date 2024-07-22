package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.DetalleDeCarrito;
import com.example.demo.Modelos.DTO.InfoCarritoDto;
import com.example.demo.Modelos.DTO.InfoDetalleCarritoDto;

@Repository
public interface IDetalleDeCarritoService {

    public List<DetalleDeCarrito> listarDetalleDeCarritos();
	public Optional<DetalleDeCarrito> obtenerDetalleDeCarrito(int codigoDCarrito);
	public DetalleDeCarrito modificarDetalleCarrito(int codigoDCarrito, DetalleDeCarrito deCarrito);
	public DetalleDeCarrito agregarDetalleDeCarrito(DetalleDeCarrito deCarrito);
	public void eliminarDetalleCarrito(int  codigoDCarrito);
	public List<InfoDetalleCarritoDto> listarInfoDetalleCarrito();
}
