package com.mx.CrudExamen.Dominio;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Rol {

	@Id
	@Column(name = "ID_ROL", columnDefinition = "NUMBER")
	private int idRol;
	
	@Column(name = "PRIVILEGIO", columnDefinition = "NVARCHAR2(50)")
	private String privilegio;
	
	@OneToMany(mappedBy = "rolId", cascade = CascadeType.ALL)
	List<Usuario> usuarios;
	
	public Rol() {}

	public Rol(int idRol, String privilegio) {
		super();
		this.idRol = idRol;
		this.privilegio = privilegio;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", privilegio=" + privilegio + "]";
	}
	
	
}
