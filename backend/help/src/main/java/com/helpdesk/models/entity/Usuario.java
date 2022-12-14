package com.helpdesk.models.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import org.hibernate.validator.constraints.br.CPF;
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
	@CPF(message = "CPF INVALIDO")
	private String cpf;

	// nome de usuario
	private String usuario;
	
	@Column(nullable = true, columnDefinition = "TEXT", length = 10000)
	private String image;
	
	// cargo ( USER, ADMIN, TECNICO )
	private String cargo;

	// admin ira avaliar o cadastro do cliente ( e assim permitir o acesso ao sistema )
	@Column(nullable = false)
	private Boolean permissao;
}
