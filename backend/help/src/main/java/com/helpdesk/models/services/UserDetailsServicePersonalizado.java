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
public class UserDetailsServicePersonalizado implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetailsPersonalizado loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Usuario usuarioRepository = repository.findByCpf(cpf);

		if (usuarioRepository == null) {
			throw new UsernameNotFoundException("Usuario não foi encontrado no banco");
		} else {
		}

		return new UserDetailsPersonalizado(usuarioRepository);
	}

}
