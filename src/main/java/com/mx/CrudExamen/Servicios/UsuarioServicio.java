package com.mx.CrudExamen.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.CrudExamen.Dao.UsuarioDao;
import com.mx.CrudExamen.Dominio.Rol;
import com.mx.CrudExamen.Dominio.Usuario;

@Service
public class UsuarioServicio implements IServicios<Usuario>{
	
	@Autowired
	private UsuarioDao dao;

	@Override
	public List<Usuario> mostrar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idUsuario"));
	}

	@Override
	public Usuario buscar(Usuario usuarioBuscar) {
		return dao.findById(usuarioBuscar.getIdUsuario()).orElse(null);
	}

	@Override
	public void guardar(Usuario usuarioNuevo) {
		String email = usuarioNuevo.getNombre().toLowerCase() + "." + usuarioNuevo.getApellidoP().toLowerCase() + "@enucom.com.mx";
		usuarioNuevo.setCorreo(email);
		dao.save(usuarioNuevo);
	}

	@Override
	public void editar(Usuario usuarioEditar) {
		dao.save(usuarioEditar);
	}

	@Override
	public void eliminar(Usuario usuarioEliminar) {
		dao.delete(usuarioEliminar);
	}
	
	public List<Usuario> obtenerUsuarioPorNombreCompleto(Usuario usuarioBuscar){
		return dao.obtenerUsusarioPorNombreCompleto(usuarioBuscar.getNombre(), usuarioBuscar.getApellidoP(), usuarioBuscar.getApellidoM());
	}

	public Rol obtenerRolPorId(Rol rolBuscar) {
		return dao.obtenerRolPorId(rolBuscar.getIdRol());
	}
}
