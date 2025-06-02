package com.mx.CrudExamen.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.CrudExamen.Dao.RolDao;
import com.mx.CrudExamen.Dominio.Rol;

@Service
public class RolServicio implements IServicios<Rol>{
	
	@Autowired
	private RolDao dao;

	@Override
	public List<Rol> mostrar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "IdRol"));
	}

	@Override
	public Rol buscar(Rol rolBuscar) {
		return dao.findById(rolBuscar.getIdRol()).orElse(null);
	}

	@Override
	public void guardar(Rol rolNuevo) {
		dao.save(rolNuevo);
	}

	@Override
	public void editar(Rol rolEditar) {
		dao.save(rolEditar);
	}

	@Override
	public void eliminar(Rol rolEliminar) {
		dao.delete(rolEliminar);
	}
	
	public List<Rol> obtenerRolPorPrivilegio(String privilegio){
		return dao.obtenerRolPorPrivilegio(privilegio);
	}

}
