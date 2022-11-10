package com.helpdesk.models.dtos;

import com.helpdesk.models.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	private Long id;
	
	private String usuario;

	private String cpf;
	

	
	public UsuarioDTO(Usuario u) {
		id = u.getId();
		usuario = u.getUsuario();
		cpf = u.getCpf();
	}
}
