package com.helpdesk.config.filters;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.models.entity.Usuario;
import com.helpdesk.models.services.UserDetailsPersonalizado;

public class FiltroAutenticacaoCustomizado extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public FiltroAutenticacaoCustomizado(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

			UsernamePasswordAuthenticationToken tokenAutenticacao = new UsernamePasswordAuthenticationToken(
					usuario.getCpf(), usuario.getSenha());

			return authenticationManager.authenticate(tokenAutenticacao);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		UserDetailsPersonalizado usuario = (UserDetailsPersonalizado) authentication.getPrincipal();

		Algorithm algoritmo = Algorithm.HMAC256("secret".getBytes());

		// data e hora local + minutos 
	
		String tokenAcesso = JWT.create()
				.withClaim("id", usuario.getInt())
				.withSubject(usuario.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("roles", usuario.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algoritmo);


		Map<String, String> tokens = new HashMap<>();
		tokens.put("token_acesso", tokenAcesso);

		response.setContentType(APPLICATION_JSON_VALUE);

		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
