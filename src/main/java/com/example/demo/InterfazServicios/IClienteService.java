package com.example.demo.InterfazServicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Modelos.Cliente;

@Repository
public interface IClienteService {

	public List<Cliente> listarClientes();
	public Optional<Cliente> obtenerCliente(int codigoCli);
	public Cliente modificarCliente(int codigoCli, Cliente cliente);
	public Cliente agregarCliente(Cliente cliente);
	public Cliente eliminarCliente(int codigoCli);
}
