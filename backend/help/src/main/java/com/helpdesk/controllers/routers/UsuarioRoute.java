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
import com.helpdesk.models.services.UserDetailsPersonalizado;
import com.helpdesk.models.services.UserDetailsServicePersonalizado;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("usuarios")
public class UsuarioRoute {

	@Autowired
	private UsuarioProcess process;
	
	@Autowired
	private UserDetailsServicePersonalizado service;

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
	
	@GetMapping("/token/atualizado")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                System.out.println("REFRESH " + refresh_token);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String cpf = decodedJWT.getSubject();
                
                System.out.println("CPF "+cpf);
                UserDetailsPersonalizado user = service.loadUserByUsername(cpf);
                
                System.out.println("USUARIO " + user);
                
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                
               
                Map<String, String> tokens = new HashMap<>();
                tokens.put("token_acesso", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
	
}