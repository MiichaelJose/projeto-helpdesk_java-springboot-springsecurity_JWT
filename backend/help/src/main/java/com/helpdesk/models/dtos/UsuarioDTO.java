package com.helpdesk.models.dtos;

import com.helpdesk.models.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UsuarioDTO {
	private Long id;

	private String usuario;

	private String cpf;

	public UsuarioDTO(Usuario u) {
		id = u.getId();
		usuario = u.getUsuario();
		cpf = u.getCpf();
	}

	public UsuarioDTO(Long id, String usuario, String cpf) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
