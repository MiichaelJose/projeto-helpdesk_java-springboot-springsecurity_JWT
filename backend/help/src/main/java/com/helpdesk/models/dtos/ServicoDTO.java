package com.helpdesk.models.dtos;

import com.helpdesk.models.entity.Servico;
import com.helpdesk.models.entity.Usuario;

import lombok.Data;

@Data
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
}
