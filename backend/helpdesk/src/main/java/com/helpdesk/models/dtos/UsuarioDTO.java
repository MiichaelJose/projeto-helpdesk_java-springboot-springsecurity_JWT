package com.helpdesk.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
	private Long id;
	
	private String usuario;

	private String cpf;
}
