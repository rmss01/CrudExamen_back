package com.mx.CrudExamen.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.CrudExamen.Dominio.Rol;
import com.mx.CrudExamen.Dominio.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

	@Query(value = "SELECT * FROM USUARIOS WHERE UPPER(NOMBRE) = UPPER(:nombre) AND UPPER(APP) = UPPER(:apellidoP) AND UPPER(APM) = UPPER(:apellidoM)", nativeQuery = true)
	public List<Usuario> obtenerUsusarioPorNombreCompleto(String nombre, String apellidoP, String apellidoM);
	
	@Query(value = "SELECT * FROM ROLES WHERE ID_ROL = :idRol", nativeQuery = true)
	public Rol obtenerRolPorId(int idRol);
	
	@Query(value = "SELECT * FROM USUARIOS WHERE UPPER(CORREO) = UPPER(:correo)", nativeQuery = true)
	public List<Usuario> obtenerCorreo(String correo);
}
