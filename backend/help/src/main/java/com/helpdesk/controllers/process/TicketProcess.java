package com.helpdesk.controllers.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.helpdesk.models.dtos.TicketDTO;
import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.repositorys.TicketRepository;

@Component
public class TicketProcess {

	@Autowired
	private TicketRepository repositoryTicket;

	//@Autowired
	//private UsuarioRepository repositoryUsuario;

	// cadastrar ticket
	public ResponseEntity<?> criarTicket(Ticket ticketDTO) {
		repositoryTicket.save(ticketDTO);

		return ResponseEntity.ok().build();
	}

	// deletar ticket
	public ResponseEntity<?> deletarTicket(Long id) {
		try {
			repositoryTicket.deleteById(id);

			return ResponseEntity.ok().build();
		} catch (EmptyResultDataAccessException a) {

			return ResponseEntity.notFound().build();
		}
	}

	// listar todos os tickets
	public ResponseEntity<List<TicketDTO>> listarTickets() {
		List<TicketDTO> lisTickets = new ArrayList<>();

		for (Ticket ticket : repositoryTicket.findAll()) {
			lisTickets.add(new TicketDTO(ticket));
		}

		return ResponseEntity.ok().body(lisTickets);
	}

	// listar todos os tickets
	public ResponseEntity<TicketDTO> listarTicket(Long id) {
		Optional<Ticket> ticketRepository = repositoryTicket.findById(id);

		Ticket ticket = ticketRepository.get();

		return ResponseEntity.ok().body(new TicketDTO(ticket));
	}
}
