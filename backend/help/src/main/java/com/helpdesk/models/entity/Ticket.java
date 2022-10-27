package com.helpdesk.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.helpdesk.models.dtos.TicketDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_fk", nullable = false)
	private Usuario idUsuario;

	// o usuario vai definir ao nivel de importancia em que deve ser solucionado o seu problema
	private String prioridade;

	// departamento em que se encontra o equipamento
	private String departamento;

	// cor do equipamento						
	private String cor;

	// data em que foi gerado o ticket
	private String data;

	// sobre o problema em que se encontra o equipamento
	private String descricao;

	// numero de fabrica do equipamento ( não obrigatório )
	private String serial;

	// nome do produto
	private String equipamento;

	// foto do produto ão obrigatório
	@Column(nullable = true, columnDefinition = "TEXT", length = 10000)
	private String imagem;
}
