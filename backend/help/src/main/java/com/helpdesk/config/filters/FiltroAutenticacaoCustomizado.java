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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.models.entity.Usuario;
import com.helpdesk.models.services.UserDetailsPersonalizado;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FiltroAutenticacaoCustomizado extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			System.out.println("ENTROU 1");
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
		System.out.println("ENTROU " + authentication.getPrincipal());
		UserDetailsPersonalizado usuario = (UserDetailsPersonalizado) authentication.getPrincipal();

		Algorithm algoritmo = Algorithm.HMAC256("secret".getBytes());

		// data e hora local + minutos 
	
		System.out.println("DATA E HORA ATUAL + MINUTOS "+new Date(System.currentTimeMillis() + 10 * 60 * 1000));
		//System.out.println("TEMPO " + new Date(System.currentTimeMillis() + 10 * 60 * 1000));
		
		String tokenAcesso = JWT.create().withClaim("id", usuario.getInt()).withSubject(usuario.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURI().toString()).withClaim("roles", usuario.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algoritmo);

		String tokenAtualizado = JWT.create().withSubject(usuario.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				// .withIssuer(request.getRequestURI().toString()).withClaim("roles",
				// usuario.getAuthorities().stream()
				// .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.withIssuer(request.getRequestURI().toString()).sign(algoritmo);
		/*
		 * response.setHeader("access_token", access_token);
		 * response.setHeader("refresh_token", refresh_token);
		 */
		Map<String, String> tokens = new HashMap<>();
		tokens.put("token_acesso", tokenAcesso);
		tokens.put("token_atualizado", tokenAtualizado);

		response.setContentType(APPLICATION_JSON_VALUE);

		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
