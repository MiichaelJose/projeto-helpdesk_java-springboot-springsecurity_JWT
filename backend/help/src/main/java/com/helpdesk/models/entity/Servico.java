package com.helpdesk.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario_tecnico")
	private Usuario idTecnico;

	@ManyToOne
	@JoinColumn(name = "id_usuario_admin")
	private Usuario idAdmin;

	@ManyToOne
	@JoinColumn(name = "id_ticket")
	private Ticket idTicket;

	// finalizado, analise, processo
	private String statusServico;

	private String statusTicket;

	private String dataInicializado;

	private String dataFinalizado;

}