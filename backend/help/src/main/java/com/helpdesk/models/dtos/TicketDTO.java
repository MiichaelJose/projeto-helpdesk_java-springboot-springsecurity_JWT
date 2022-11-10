package com.helpdesk.models.dtos;

import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TicketDTO {
	private Long id;

	private UsuarioDTO usuario;

	private String prioridade;

	private String departamento;

	private String cor;

	private String data;

	private String descricao;

	private String serial;

	private String equipamento;

	private String imagem;
	
	
	public TicketDTO(Ticket ticket) {
		id = ticket.getId();
		usuario = new UsuarioDTO(ticket.getIdUsuario());
		descricao = ticket.getDescricao();
		prioridade = ticket.getPrioridade();
		departamento = ticket.getDepartamento();
		cor = ticket.getCor();
		data = ticket.getData();
		serial = ticket.getSerial();
		equipamento = ticket.getEquipamento();
		imagem = ticket.getImagem();
	}
	
}
