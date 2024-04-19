package com.helpdesk.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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

	// Tecnico aceitar servico (aceito, observacao, recusado) 
	private String tecnicoAceitarServico;

	private String dataServicoInicializado;

	private String dataServicoFinalizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Usuario idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Usuario getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Usuario idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Ticket getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Ticket idTicket) {
		this.idTicket = idTicket;
	}

	public String getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(String statusServico) {
		this.statusServico = statusServico;
	}

	public String getTecnicoAceitarServico() {
		return tecnicoAceitarServico;
	}

	public void setTecnicoAceitarServico(String tecnicoAceitarServico) {
		this.tecnicoAceitarServico = tecnicoAceitarServico;
	}

	public String getDataServicoInicializado() {
		return dataServicoInicializado;
	}

	public void setDataServicoInicializado(String dataServicoInicializado) {
		this.dataServicoInicializado = dataServicoInicializado;
	}

	public String getDataServicoFinalizado() {
		return dataServicoFinalizado;
	}

	public void setDataServicoFinalizado(String dataServicoFinalizado) {
		this.dataServicoFinalizado = dataServicoFinalizado;
	}

	
	
}
