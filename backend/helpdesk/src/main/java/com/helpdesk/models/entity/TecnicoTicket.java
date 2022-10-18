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
public class TecnicoTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_tecnico")
	private Usuario idTecnico;
	
	
	@ManyToOne
	@JoinColumn(name = "id_ticket")
	private Ticket idTicket;
}
