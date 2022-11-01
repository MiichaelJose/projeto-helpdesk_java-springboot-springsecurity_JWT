package com.helpdesk.controllers.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.helpdesk.models.dtos.TicketDTO;
import com.helpdesk.models.dtos.UsuarioDTO;
import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.entity.Usuario;
import com.helpdesk.models.repositorys.TicketRepository;
import com.helpdesk.models.repositorys.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketProcess {

	@Autowired
	private TicketRepository repositoryTicket;

	@Autowired
	private UsuarioRepository repositoryUsuario;

	// cadastrar ticket
	public ResponseEntity<?> criarTicket(Ticket ticketDTO) {
		repositoryTicket.save(ticketDTO);
		
		log.info("--- Ticket cadastrado");
		
		return ResponseEntity.ok().build();
	}

	// deletar ticket
	public ResponseEntity<?> deletarTicket(Long id) {
		try {
			repositoryTicket.deleteById(id);
					
			log.info("--- Ticket deletado");
			
			return ResponseEntity.ok().build();
		} catch (EmptyResultDataAccessException a) {
			log.error("-- Ticket não encontrado");
			
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
