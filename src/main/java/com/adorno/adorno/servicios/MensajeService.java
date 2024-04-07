package com.adorno.adorno.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adorno.adorno.modelo.entity.Mensaje;
import com.adorno.adorno.repositorios.MensajeRepository;
import com.adorno.adorno.repositorios.UsuarioRepository;

@Service
public class MensajeService implements ServiceFeria<Mensaje, Long>{

	private final MensajeRepository mensajeRepo;
	private final UsuarioRepository usuarioRepo;
	
	public MensajeService(MensajeRepository mensajeRepo, UsuarioRepository usuarioRepo) {
		super();
		this.mensajeRepo = mensajeRepo;
		this.usuarioRepo = usuarioRepo;
	}

	@Override
	public ResponseEntity<?> getAll() {
		List<Mensaje> mensajes = this.mensajeRepo.findAll();
		return ResponseEntity.ok(mensajes);
	}

	@Override
	public ResponseEntity<?> getById(Long id) {
		Optional<Mensaje> mensajeOptional =this.mensajeRepo.findById(id);
		if(mensajeOptional.isPresent()) {
			return ResponseEntity.ok(mensajeOptional.get());
		}
		return ResponseEntity.badRequest().body("No existe el mensaje "+id);
	}

	@Override
	public ResponseEntity<?> create(Mensaje t) {
		
		if(this.usuarioRepo.findById(t.getUsuario().getNombreUsuario()).isEmpty()) {
			this.usuarioRepo.save(t.getUsuario());
		}
		
		Mensaje mensaje = this.mensajeRepo.save(t);
		return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
	}

	

	
}
