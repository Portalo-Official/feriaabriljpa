package com.adorno.adorno.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.adorno.adorno.modelo.entity.Usuario;
import com.adorno.adorno.repositorios.UsuarioRepository;

public class UsuarioService implements Service<Usuario, String>{

	private final UsuarioRepository UsuarioRepo;

	public UsuarioService(UsuarioRepository usuarioRepo) {
		super();
		UsuarioRepo = usuarioRepo;
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO puiede que no haga falta - Chavero 07/04 - 19:35
		List<Usuario> usuarios=this.UsuarioRepo.findAll();
		return ResponseEntity.ok(usuarios);
	}

	@Override
	public ResponseEntity<?> getById(String id) {
//		Optional<String> nombreMarca=Optional.of("");
//		Optional<Marca> marca = this.marcaRepo.findById(id);
//		if(marca.isPresent()) {
//			nombreMarca = Optional.of(marca.get().getNombre());	
//		}
//		return nombreMarca;
		
		Optional<Usuario> usuarioOpt = this.UsuarioRepo.findById(id);
		if(usuarioOpt.isPresent()) {
			return ResponseEntity.ok(usuarioOpt.get());
		}
		return ResponseEntity.badRequest().body("Usuario no encontrado");
	}

	@Override
	public ResponseEntity<?> create(Usuario t) {
		
		if(this.UsuarioRepo.findById(t.getNombreUsuario()).get()!= null) {
			this.UsuarioRepo.save(t);
			return ResponseEntity.ok(true);
		}
		
		return ResponseEntity.badRequest().body(false);
	}
	
	
	
	
	
}
