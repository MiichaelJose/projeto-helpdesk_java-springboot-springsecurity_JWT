package com.helpdesk.controller;

import com.helpdesk.model.dtos.TicketDTO;
import com.helpdesk.model.entity.Ticket;
import com.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketRepository repositoryTicket;

    public ResponseEntity<?> create(Ticket ticketDTO) {
        repositoryTicket.save(ticketDTO);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            repositoryTicket.deleteById(id);

            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException a) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<TicketDTO>> list() {
        List<TicketDTO> listTickets = new ArrayList<>();

        for (Ticket ticket : repositoryTicket.findAll()) {
            listTickets.add(new TicketDTO(ticket));
        }

        return ResponseEntity.ok().body(listTickets);
    }

    /*public ResponseEntity<TicketDTO> listarTicket(Long id) {
        Optional<Ticket> ticketRepository = repositoryTicket.findById(id);

        Ticket ticket = ticketRepository.get();

        return ResponseEntity.ok().body(new TicketDTO(ticket));
    }*/
}
