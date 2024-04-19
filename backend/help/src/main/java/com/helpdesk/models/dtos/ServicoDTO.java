package com.helpdesk.models.dtos;

import com.helpdesk.models.entity.Servico;

public class ServicoDTO {

	private UsuarioDTO tecnico;

	private TicketDTO ticket;

	private String statusServico;

	private String tecnicoAceitarServico;

	private String dataServicoInicializado;

	private String dataServicoFinalizado;

	public ServicoDTO(Servico s) {
		tecnico = new UsuarioDTO(s.getIdAdmin());

		ticket = new TicketDTO(s.getIdTicket());

		statusServico = s.getStatusServico();

		tecnicoAceitarServico = s.getTecnicoAceitarServico();

		dataServicoInicializado = s.getDataServicoInicializado();

		dataServicoFinalizado = s.getDataServicoFinalizado();
	}

	public UsuarioDTO getTecnico() {
		return tecnico;
	}

	public void setTecnico(UsuarioDTO tecnico) {
		this.tecnico = tecnico;
	}

	public TicketDTO getTicket() {
		return ticket;
	}

	public void setTicket(TicketDTO ticket) {
		this.ticket = ticket;
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
