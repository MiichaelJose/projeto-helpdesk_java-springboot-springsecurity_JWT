package com.helpdesk.controllers.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.helpdesk.models.dtos.UsuarioDTO;
import com.helpdesk.models.entity.Usuario;
import com.helpdesk.models.repositorys.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Michael Dev
 *
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UsuarioProcess {

	@Autowired
	private UsuarioRepository repository;

	private final PasswordEncoder passwordEncoder;

	// cadastrar
	// o usuario sera avaliado por admin, se sim, tera acesso a o sistema
	public ResponseEntity<?> criarUsuario(Usuario usuario) {
		try {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			if (repository.save(usuario) != null) {
				log.info("--- Usuario cadastrado!");
				return new ResponseEntity<String>("Usuario cadastrado com sucesso", HttpStatus.CREATED);
			}

			return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			log.info("--- Usuario já cadastrado!");
			return new ResponseEntity<String>("Usuario já cadastrado!", HttpStatus.BAD_REQUEST);
		}
	}

	// listar
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	// listar usuario que esta solicitando acesso
	public ResponseEntity<List<Usuario>> listarUsuariosAcesso() {
		List<Usuario> lisUsuarios = new ArrayList<>();

		for (Usuario usuario : repository.findAll()) {
			if (!usuario.getPermissao()) {
				lisUsuarios.add(usuario);
			}
		}

		return new ResponseEntity<>(lisUsuarios, HttpStatus.OK);
	}

	// listar unico usuario
	public ResponseEntity<UsuarioDTO> listarUsuario(Long id) {
		Optional<Usuario> usuarioRepository = repository.findById(id);

		Usuario usuario = usuarioRepository.get();

		return new ResponseEntity<>(new UsuarioDTO(usuario.getId(), usuario.getUsuario(), usuario.getCpf()),
				HttpStatus.OK);
	}

	// deletar
	public ResponseEntity<?> deletarUsuario(Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<String>("deletado com sucesso!", HttpStatus.OK);
		} catch (EmptyResultDataAccessException a) {
			System.out.println("Tipo de informações não aceito!");
			return new ResponseEntity<String>("recurso não encontrado!", HttpStatus.NOT_FOUND);
		}
	}

	// alterar
	public ResponseEntity<?> alterarUsuario(Long id, Usuario usuario) {
		try {
			Usuario usuarioAtualizado = repository.findById(id).map(info -> {
				info.setUsuario(usuario.getUsuario());
				info.setSenha(passwordEncoder.encode(usuario.getSenha()));
				
				return repository.save(info);
			}).orElse(null);
			
			if (usuarioAtualizado == null) {
				return new ResponseEntity<>("Recurso não encontrado!", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>("Recurso alterado!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Recurso não encontrado!", HttpStatus.BAD_REQUEST);
		}

	}
	
	// alterar permissao usuario
	public ResponseEntity<?> alterarUsuarioPermissao(Long id, Usuario usuario) {
		try {
			Usuario usuarioPermissao = repository.findById(id).map(info -> {
				info.setPermissao(usuario.getPermissao());
				return repository.save(info);
			}).orElse(null);
			
			if (usuarioPermissao == null) {
				return new ResponseEntity<>("Recurso não encontrado!", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>("Recurso alterado!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Recurso não encontrado!", HttpStatus.BAD_REQUEST);
		}

	}

}