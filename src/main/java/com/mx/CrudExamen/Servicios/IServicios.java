package com.mx.CrudExamen.Servicios;

import java.util.List;

public interface IServicios <T> {

	public List<T> mostrar();
	
	public T buscar(T elem);
	
	public void guardar(T elem);
	
	public void editar(T elem);
	
	public void eliminar(T elem);
}
