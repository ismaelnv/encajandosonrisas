package com.example.demo.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaz.ICliente;
import com.example.demo.InterfazServicios.IClienteService;
import com.example.demo.Modelos.Cliente;


@Service
public class ClienteService implements IClienteService  {

    @Autowired
    private ICliente clienteData;

    @Override
    public List<Cliente> listarClientes() {
        
        List<Cliente> clientes = (List<Cliente>)clienteData.findAll();

        return clientes;
    }

    @Override
    public Optional<Cliente> obtenerCliente(int codigoCli) {
        
        if(codigoCli == 0){

            return null;
        }

        Optional<Cliente> cliente =  clienteData.findById(codigoCli);

        return cliente;
    }

    @Override
    public Cliente modificarCliente(int codigoCli, Cliente cliente) {
        
        Optional<Cliente> clien = this.obtenerCliente(codigoCli);

        if (cliente == null) {
            
            return cliente;
        }

		if (clien.isPresent()) {

			Cliente c =  clienteData.save(cliente);
            return c;
		}

		return null;
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        
        if (cliente == null) {

            return null;
        }
        
        return clienteData.save(cliente);
    }

    @Override
    public Cliente eliminarCliente(int codigoCli) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCliente'");
       
    }

}
