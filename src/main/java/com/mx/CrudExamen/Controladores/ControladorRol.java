package com.mx.CrudExamen.Controladores;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.CrudExamen.Dominio.Rol;
import com.mx.CrudExamen.Servicios.RolServicio;

@RestController
@RequestMapping(path = "curso/roles")
@CrossOrigin
public class ControladorRol {

	@Autowired
	private RolServicio servicio;
	
	@GetMapping("/mostrar")
	public ResponseEntity<?> mostrar(){
		List<Rol> rolesEncontrados = servicio.mostrar();
		return ResponseEntity.ok(rolesEncontrados);
	}
	
	@PostMapping("/buscar")
	public ResponseEntity<?> buscar(@RequestBody Rol rol){
		Rol encontrado = servicio.buscar(rol);
		if(encontrado == null) {
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "No existe un rol con id " + rol.getIdRol());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		} else {
			return ResponseEntity.ok(encontrado);
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody Rol rol){
		Rol encontrado = servicio.buscar(rol);
		HashMap<String, String> mensaje = new HashMap<>();
		
		if(encontrado != null) {
			mensaje.put("mensaje", "Ya existe un rol con id " + rol.getIdRol());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
		} else if(!servicio.obtenerRolPorPrivilegio(rol.getPrivilegio()).isEmpty()) {
			mensaje.put("mensaje", "Ya existe un rol con el privilegio " + rol.getPrivilegio());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
		} else {
			servicio.guardar(rol);
			mensaje.put("mensaje", "Se ha creado el rol " + rol);
			return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
		}
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@RequestBody Rol rol){
		Rol encontrado = servicio.buscar(rol);
		HashMap<String, String> mensaje = new HashMap<>();
		
		if(encontrado == null) {
			mensaje.put("mensaje", "No existe un rol con id " + rol.getIdRol());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		} else if(rol.getPrivilegio().equals(encontrado.getPrivilegio())) {
			mensaje.put("mensaje", "Nada que actualizar");
			return ResponseEntity.ok(mensaje);
		} else if(!servicio.obtenerRolPorPrivilegio(rol.getPrivilegio()).isEmpty()) {
			mensaje.put("mensaje", "Ya existe un rol con el privilegio " + rol.getPrivilegio());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
		} else {
			servicio.editar(rol);
			mensaje.put("mensaje", "Rol actualizado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mensaje);
		}
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Rol rol){
		Rol encontrado = servicio.buscar(rol);
		HashMap<String, String> mensaje = new HashMap<>();
		
		if(encontrado == null) {
			mensaje.put("mensaje", "No existe el rol con id " + rol.getIdRol());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		} else {
			servicio.eliminar(encontrado);
			mensaje.put("mensaje", "Se ha eliminado el rol " + encontrado);
			return ResponseEntity.ok().body(mensaje);
		}
	}
}
