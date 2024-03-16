package com.example.demo.InterfazServicios;

import java.util.List;

import com.example.demo.Modelos.Usuario;

public interface IUsuarioServices {
	
	public List<Usuario> listarUsuarios();
	public Usuario  obtenerUsuario(int id);
	public Usuario modificarUsuario(int id, Usuario usu);
	public Usuario agregarUsuario(Usuario usu);
	public Usuario eliminarUsuario(int id);
}
