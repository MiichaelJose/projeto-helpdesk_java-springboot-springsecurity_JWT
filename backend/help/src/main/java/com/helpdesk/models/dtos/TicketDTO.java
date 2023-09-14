package com.helpdesk.models.dtos;

import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
