package com.helpdesk.models.services;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helpdesk.models.entity.Usuario;
import com.helpdesk.models.repositorys.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServicePersonalizado implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Usuario usuarioRepository = repository.findByUsuario(usuario);
		if (usuarioRepository == null) {
			log.error("Usuario não foi encontrado no banco");
			throw new UsernameNotFoundException("Usuario não foi encontrado no banco");
		} else {
			log.error("Usuario foi encontrado no banco: {}", usuarioRepository);
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(usuarioRepository.getCargo()));

		authorities.forEach(role -> {
			System.out.println("role autorizacao 2" + role);
		});

		return new org.springframework.security.core.userdetails.User(usuarioRepository.getUsuario(),
				usuarioRepository.getSenha(), authorities);
	}

}