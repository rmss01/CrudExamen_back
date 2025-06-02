package com.mx.CrudExamen.Controladores;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;


import org.json.JSONObject;
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
import com.mx.CrudExamen.Dominio.Usuario;
import com.mx.CrudExamen.Servicios.UsuarioServicio;

@RestController
@RequestMapping(path = "curso/usuarios")
@CrossOrigin
public class ControladorUsuario {

	@Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/mostrar")
	public ResponseEntity<?> mostrar() {
		List<Usuario> listaUsuarios = servicio.mostrar();
		return ResponseEntity.ok(listaUsuarios);
	}
	
	@PostMapping("/buscar")
	public ResponseEntity<?> buscar(@RequestBody Usuario usuario){
		Usuario encontrado = servicio.buscar(usuario);
		if(encontrado != null) {
			return ResponseEntity.ok(encontrado);
		} else {
			HashMap<String, String> mensaje= new HashMap<>();
			mensaje.put("mensaje", "No existe un usuario con id " + usuario.getIdUsuario());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
		Usuario encontrado = servicio.buscar(usuario);
		String caracteresEspeciales = ".*[0-9 !\"#$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~].*";
		
		Date hoyHace18anios = new Date();		
		LocalDate localHoyHace18anios = LocalDate.now().minusYears(18);
		hoyHace18anios = Date.from(localHoyHace18anios.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		HashMap<String, String> mensaje = new HashMap<>();
		
		if(encontrado != null) {
			mensaje.put("mensaje", "Ya existe un usuario con id " + usuario.getIdUsuario());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
		} else if(!servicio.obtenerUsuarioPorNombreCompleto(usuario).isEmpty()) {
			mensaje.put("mensaje", "Ya existe un usuario con el nombre " + usuario.getNombre() + " " + usuario.getApellidoP() + " " + usuario.getApellidoM());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
		} else if(usuario.getNombre().matches(caracteresEspeciales) || usuario.getApellidoP().matches(caracteresEspeciales) || usuario.getApellidoM().matches(caracteresEspeciales)) {
			mensaje.put("mensaje", "El nombre y apellidos solo pueden contener caracteres de la a - z");
			System.out.println(mensaje);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		}else if(usuario.getFechaNacimiento().after(hoyHace18anios))
		{
			mensaje.put("mensaje", "El usuario debe ser mayor a 18 años");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		} else {
			Rol rolEncontrado = servicio.obtenerRolPorId(usuario.getRolId());
			
			if(rolEncontrado == null){
				mensaje.put("mensaje", "No existe un rol con el id " + usuario.getRolId().getIdRol());
				return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
			} else {
				usuario.setRolId(rolEncontrado);
				servicio.guardar(usuario);
				System.out.println(usuario);
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		}
			
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@RequestBody Usuario usuario){
		Usuario encontrado = servicio.buscar(usuario);
		String caracteresEspeciales = ".*[0-9 !\"#$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~].*";
		Rol rolEncontrado = servicio.obtenerRolPorId(usuario.getRolId());
		HashMap<String, String> mensaje = new HashMap<>();
		
		Date hoyHace18anios = new Date();		
		LocalDate localHoyHace18anios = LocalDate.now().minusYears(18);
		hoyHace18anios = Date.from(localHoyHace18anios.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if(encontrado == null) {
			mensaje.put("mensaje", "No existe un usuario con id " + usuario.getIdUsuario());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
		} else if(usuario.getNombre().equals(encontrado.getNombre()) && usuario.getApellidoP().equals(encontrado.getApellidoP()) && usuario.getApellidoM().equals(encontrado.getApellidoM())) {
			if(usuario.getFechaNacimiento().after(hoyHace18anios)) {
				mensaje.put("mensaje", "El usuario debe ser mayor a 18 años");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
			}else if(rolEncontrado == null){
				mensaje.put("mensaje", "No existe un rol con el id " + usuario.getRolId().getIdRol());
				return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
			} else {
				mensaje.put("mensaje", "Usuario " + usuario + " editado exitosamente");
				usuario.setRolId(rolEncontrado);
				servicio.guardar(usuario);
				return ResponseEntity.ok(mensaje);
			}
		} else {
			if(!servicio.obtenerUsuarioPorNombreCompleto(usuario).isEmpty()) {
				mensaje.put("mensaje", "Ya existe un usuario con el nombre " + usuario.getNombre() + " " + usuario.getApellidoP() + " " + usuario.getApellidoM());
				return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
			} else if(usuario.getNombre().matches(caracteresEspeciales) || usuario.getApellidoP().matches(caracteresEspeciales) || usuario.getApellidoM().matches(caracteresEspeciales)) {
				mensaje.put("mensaje", "El nombre y apellidos solo pueden contener caracteres de la a - z");
				System.out.println(mensaje);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
				
			} else {
				
				if(usuario.getFechaNacimiento().after(hoyHace18anios)) {
					mensaje.put("mensaje", "El usuario debe ser mayor a 18 años");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
				}else if(rolEncontrado == null){
					mensaje.put("mensaje", "No existe un rol con el id " + usuario.getRolId().getIdRol());
					return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
				} else {
					mensaje.put("mensaje", "Usuario " + usuario + " editado exitosamente");
					usuario.setRolId(rolEncontrado);
					servicio.guardar(usuario);
					return ResponseEntity.ok(mensaje);
				}
			}
		}
		
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Usuario usuario){
		Usuario encontrado = servicio.buscar(usuario);
		HashMap<String, String> mensaje = new HashMap<>();
		if(encontrado == null) {
			mensaje.put("mensaje", "No existe el usuario con id " + usuario.getIdUsuario());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
		} else {
			mensaje.put("mensaje", "Se ha eliminado el usuario con id " + usuario.getIdUsuario());
			servicio.eliminar(encontrado);
			return ResponseEntity.ok(mensaje);
		}
	}
}
