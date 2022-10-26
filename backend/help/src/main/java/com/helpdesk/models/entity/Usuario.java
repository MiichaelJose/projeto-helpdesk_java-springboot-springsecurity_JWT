package com.helpdesk.models.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
// classe referece a ( clientes, administradores, técnicos ) 
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String senha;
	
	@Column(unique = true)
	private String cpf;

	// nome de usuario
	private String usuario;
	
	private String image;
	
	// cargo ( USER, ADMIN, TECNICO )
	private String cargo;

	// admin ira avaliar o cadastro do cliente ( e assim permitir o acesso ao sistema )
	private Boolean permissao;

	/*
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(getCargo()));
		
		authorities.forEach(role -> {
			System.out.println("role autorizacao 2" + role);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	*/
}
