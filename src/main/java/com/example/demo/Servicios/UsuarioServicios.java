package com.example.demo.Servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.InterfazServicios.IUsuarioServices;
import com.example.demo.Modelos.Usuario;

@Service
public class UsuarioServicios implements IUsuarioServices{
	
	private Map<Integer,Usuario> data;
	
	public UsuarioServicios() {
//		this.data = Arrays.asList(
//				new Usuario(1,"alonso@gmail.com","363456",true),
//				new Usuario(2,"ismael@gmail.com","452368",true),
//				new Usuario(3,"kevin@gmail.com","245587",true),
//				new Usuario(4,"maria@gmail.com","336699",true),
//				new Usuario(5,"david@gmail.com","448762",true),
//				new Usuario(6,"marlon@gmail.com","364125",true));
		
		Usuario u1 = new Usuario(1,"alonso@gmail.com","363456",true);
		Usuario u2 = new Usuario(2,"ismael@gmail.com","452368",true);
		Usuario u3 = new Usuario(3,"kevin@gmail.com","245587",true);
		Usuario u4 = new Usuario(4,"maria@gmail.com","336699",true);
		Usuario u5 = new Usuario(5,"david@gmail.com","448762",true);
		Usuario u6 = new Usuario(6,"marlon@gmail.com","364125",true);
		
		
		this.data = new HashMap<>();
		data.put(u1.getCod_usu(),u1);
		data.put(u2.getCod_usu(),u2);
		data.put(u3.getCod_usu(),u3);
		data.put(u4.getCod_usu(),u4);
		data.put(u5.getCod_usu(),u5);
		data.put(u6.getCod_usu(),u6);

	}
	
	@Override
	public List<Usuario> listarUsuarios(){
		return data.values().stream().collect(Collectors.toList());
	}
	
	@Override
	public Usuario obtenerUsuario(int id) {
		Predicate<Usuario> condicion = u -> u.getCod_usu()==id;
		return data.values().stream().filter(condicion).findFirst().orElse(null);
	}

	@Override
	public Usuario modificarUsuario(int id, Usuario usu) {
		Predicate<Usuario> condicion = u -> u.getCod_usu()==id;
		Usuario usuarioModificado = data.values().stream().filter(condicion).findFirst().orElse(null);
		usuarioModificado.setCorreo(usu.getCorreo());
		usuarioModificado.setContrasena(usu.getContrasena());
		
		return usuarioModificado;
	}
	
	@Override
	public Usuario agregarUsuario(Usuario usu) {
		Usuario nuevo = new Usuario();
		nuevo.setCod_usu(usu.getCod_usu());
		nuevo.setCorreo(usu.getCorreo());
		nuevo.setContrasena(usu.getContrasena());
		nuevo.setEstado(true);
		
		data.put(nuevo.getCod_usu(), nuevo);
		
		return nuevo;
	}

	@Override
	public Usuario eliminarUsuario(int id) {
		Predicate<Usuario> condicion = u -> u.getCod_usu()==id;
		Usuario usuarioEstadoEliminado = data.values().stream()
										.filter(condicion).findFirst().orElse(null);
		
		usuarioEstadoEliminado.setEstado(false);
		return usuarioEstadoEliminado;
	}


	
	
}
