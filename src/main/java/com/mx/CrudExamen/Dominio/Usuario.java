package com.mx.CrudExamen.Dominio;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@Column(name = "ID_USUARIO", columnDefinition = "NUMBER")
	private int idUsuario;
	
	@Column(name = "NOMBRE", columnDefinition = "NVARCHAR2(100)")
	private String nombre;
	
	@Column(name = "APP", columnDefinition = "NVARCHAR2(50)")
	private String apellidoP;
	
	@Column(name = "APM", columnDefinition = "NVARCHAR2(50)")
	private String apellidoM;
	
	@Column(name = "SEXO", columnDefinition = "NVARCHAR2(25)")
	private String sexo;
	
	@Column(name = "CORREO", columnDefinition = "NVARCHAR2(100)")
	private String correo;
	
	@Column(name = "FECHA_NACIMIENTO", columnDefinition = "DATE")
	private Date fechaNacimiento;
	
	@Column(name = "FECHA_CREACION", columnDefinition = "DATE")
	private Date fechaCreacion;
	
	@JoinColumn(name = "ROL_ID", columnDefinition = "NUMBER")
	@ManyToOne(fetch = FetchType.EAGER)
	private Rol rolId;
	
	public Usuario() {}

	public Usuario(int idUsuario, String nombre, String apellidoP, String apellidoM, String sexo, String correo,
			Date fechaNacimiento, Date fechaCreacion, Rol rolId) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.sexo = sexo;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaCreacion = fechaCreacion;
		this.rolId = rolId;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Rol getRolId() {
		return rolId;
	}

	public void setRolId(Rol rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM="
				+ apellidoM + ", sexo=" + sexo + ", correo=" + correo + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaCreacion=" + fechaCreacion + ", rolId=" + rolId + "]";
	}
	
	
	
}
