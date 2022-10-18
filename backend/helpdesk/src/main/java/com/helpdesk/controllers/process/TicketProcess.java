package com.helpdesk.controllers.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.repositorys.TicketRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketProcess {

	@Autowired
	private TicketRepository repository;

	// cadastrar ticket
	public ResponseEntity<?> criarTicket(Ticket ticket) {
		return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
	}

	// deletar ticket
	public ResponseEntity<?> deletarTicket(Long id) {
		return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
	}

	// listar todos os tickets
	public ResponseEntity<?> listarTickets(Long id) {
		return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
	}
}
