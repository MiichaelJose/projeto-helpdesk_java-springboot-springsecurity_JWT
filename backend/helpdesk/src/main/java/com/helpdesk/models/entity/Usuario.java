package com.helpdesk.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	// cargo ( USER, ADMIN, TECNICO )
	private String cargo;

	// admin ira avaliar o cadastro do cliente ( e assim permitir o acesso ao sistema )
	private Boolean permissao;
}