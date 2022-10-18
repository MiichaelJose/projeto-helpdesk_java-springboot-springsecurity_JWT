package com.helpdesk.controllers.routers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.controllers.process.UsuarioProcess;
import com.helpdesk.models.dtos.UsuarioDTO;
import com.helpdesk.models.entity.Usuario;

@RestController
@RequestMapping("usuarios")
public class UsuarioRoute {

	@Autowired
	private UsuarioProcess process;

	@PostMapping
	public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
		return process.criarUsuario(usuario);
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return process.listarUsuarios();
	}

	@GetMapping("solicitando-acesso")
	public ResponseEntity<List<Usuario>> listarUsuariosAcesso() {
		return process.listarUsuariosAcesso();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioDTO> listarUsuario(@PathVariable Long id) {
		return process.listarUsuario(id);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
		System.out.println("entrou");
		return process.deletarUsuario(id);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		return process.alterarUsuario(id, usuario);
	}
	
	@PutMapping("{id}/alterar-acesso")
	public ResponseEntity<?> alterarUsuarioPermissao(@PathVariable Long id, @RequestBody Usuario usuario) {
		return process.alterarUsuarioPermissao(id, usuario);
	}
	
}
