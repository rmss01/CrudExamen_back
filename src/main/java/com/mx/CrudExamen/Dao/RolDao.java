package com.mx.CrudExamen.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.CrudExamen.Dominio.Rol;

public interface RolDao extends JpaRepository<Rol, Integer>{

	@Query(value = "SELECT * FROM ROLES WHERE UPPER(PRIVILEGIO) = UPPER(:privilegio)", nativeQuery = true)
	public List<Rol> obtenerRolPorPrivilegio(String privilegio);
	
}
