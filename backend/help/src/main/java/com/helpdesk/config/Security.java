package com.helpdesk.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.helpdesk.config.filters.FiltroAutenticacaoCustomizado;
import com.helpdesk.config.filters.FiltroAutorizacaoCustomizado;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class Security {

	private final AuthenticationConfiguration authenticationConfiguration;
	
	public Security(AuthenticationConfiguration authenticationConfiguration) {
		this.authenticationConfiguration = authenticationConfiguration;
	}

	// acesso a autorização
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		FiltroAutenticacaoCustomizado customAuthenticationFilter = new FiltroAutenticacaoCustomizado(
				authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/login");

		http.csrf().disable();
		// ativar cors
		http.cors();
		http.sessionManagement().sessionCreationPolicy(STATELESS);
		// login e cadastrar
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/usuarios/**").permitAll();
		// cadastrar usuario
		// http.authorizeRequests().antMatchers(HttpMethod.POST,
		// "/usuarios/**").hasAuthority("ADMIN");
		// todos usuarios
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/usuarios", "/usuarios/solicitando-acesso")
				.hasAuthority("ADMIN");
		// usuario unico
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyAuthority("ADMIN", "USER");
		// deletar usuario
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ADMIN");
		// alterar acesso usuario
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/usuarios/{id}/alterar-acesso").hasAuthority("ADMIN");
		// alterar usuario
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/usuarios/{id}").hasAnyAuthority("ADMIN", "USER");
		// http.authorizeRequests().antMatchers(POST,
		// "/api/user/save/**").hasAuthority("ROLE_ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new FiltroAutorizacaoCustomizado(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// codificador de senha
	// É necessário definir como bean para meu outro bean utilizar
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// acesso a autenticação
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
