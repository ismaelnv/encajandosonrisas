package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.InterfazServicios.IClienteService;
import com.example.demo.Modelos.Cliente;


@RestController
@RequestMapping
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
	public List<Cliente> ObtenerClientes(){

		List<Cliente> clientes = clienteService.listarClientes();
		return clientes;
	}
    
    @PostMapping("/clientes")
	public Cliente agregarCliente(@RequestBody Cliente cli) {
    	
		return clienteService.agregarCliente(cli);
	}
    
	@GetMapping("/clientes/{codigoCliente}")
	public Optional<Cliente> obtenerClienteId(@PathVariable int codigoCliente){
		
		return clienteService.obtenerCliente(codigoCliente);
	}
	
	@PutMapping("/clientes/{codigoCliente}")
	public Cliente editarCliente(@PathVariable int codigoCliente, @RequestBody Cliente cli) {
		
		Cliente clienteM =  clienteService.modificarCliente(codigoCliente, cli);
		return clienteM;
	}
	
}
