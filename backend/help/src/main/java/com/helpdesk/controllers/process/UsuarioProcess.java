package com.helpdesk.controllers.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
	public ResponseEntity<?> criarUsuario(@Validated Usuario usuario) {
		try {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

			if (repository.save(usuario) != null) {

				log.info("--- Usuario cadastrado!");

				return ResponseEntity.ok().build();
			}
			log.info("--- Usuario null!");

			return ResponseEntity.badRequest().build();
		} catch (DataIntegrityViolationException e) {

			log.info("--- Usuario já cadastrado!");

			return ResponseEntity.badRequest().build();
		}
	}

	// listar
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> lisUsuarios = new ArrayList<>();

		for (Usuario usuario : repository.findAll()) {
			if (!usuario.getCargo().equals("ADMIN")) {
				lisUsuarios.add(usuario);
			}
		}

		return ResponseEntity.ok().body(lisUsuarios);
	}

	// listar usuario que esta solicitando acesso
	public ResponseEntity<List<Usuario>> listarUsuariosAcesso() {
		List<Usuario> lisUsuarios = new ArrayList<>();

		for (Usuario usuario : repository.findAll()) {
			if (!usuario.getCargo().equals("ADMIN")) {
				lisUsuarios.add(usuario);
			}
		}

		return ResponseEntity.ok().body(lisUsuarios);
	}

	// listar unico usuario
	public ResponseEntity<UsuarioDTO> listarUsuario(Long id) {
		Optional<Usuario> usuarioRepository = repository.findById(id);

		Usuario usuario = usuarioRepository.get();

		return ResponseEntity.ok().body(new UsuarioDTO(usuario.getId(), usuario.getUsuario(), usuario.getCpf()));
	}

	// deletar
	public ResponseEntity<?> deletarUsuario(Long id) {
		try {
			repository.deleteById(id);

			log.info("--- Usuario deletado");

			return ResponseEntity.ok().build();
		} catch (EmptyResultDataAccessException a) {
			log.info("--- Usuario não encontrado");

			return ResponseEntity.notFound().build();
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
				log.info("--- Usuario não encontrado");

				return ResponseEntity.notFound().build();
			}

			log.info("--- Usuario alterado");

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.info("--- não encontrado");

			return ResponseEntity.badRequest().build();
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
				log.info("--- Usuario não encontrado");

				return ResponseEntity.notFound().build();
			}

			log.info("--- Usuario alterado");

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.info("--- não encontrado");

			return ResponseEntity.badRequest().build();
		}

	}

}
