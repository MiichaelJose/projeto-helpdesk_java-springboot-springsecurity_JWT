package com.helpdesk.controllers.routers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.controllers.process.TicketProcess;
import com.helpdesk.controllers.process.UsuarioProcess;
import com.helpdesk.models.dtos.TicketDTO;
import com.helpdesk.models.dtos.UsuarioDTO;
import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.entity.Usuario;

@RestController
@RequestMapping("ticket")
public class TicketRoute {

	@Autowired
	private TicketProcess process;

	@PostMapping
	public ResponseEntity<?> criarTicket(@RequestBody Ticket ticketDTO) {
		return process.criarTicket(ticketDTO);
	}

	@GetMapping
	public ResponseEntity<List<TicketDTO>> listarTickets() {
		return process.listarTickets();
	}

	@GetMapping("{id}")
	public ResponseEntity<TicketDTO> listarTicket(@PathVariable Long id) {
		return process.listarTicket(id);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deletarTicket(@PathVariable Long id) {
		return process.deletarTicket(id);
	}
	/*
	 * @PutMapping("{id}") public ResponseEntity<?> alterarTicket(@PathVariable Long
	 * id, @RequestBody Usuario usuario) { return process.alterarTicket(id,
	 * usuario); }
	 */
}
