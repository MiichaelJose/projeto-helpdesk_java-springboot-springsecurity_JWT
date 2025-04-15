package com.helpdesk.controller;

import com.helpdesk.model.entity.Service;
import com.helpdesk.repository.ServiceRepository;
import com.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public ResponseEntity<?> create(Service service) {
        serviceRepository.save(service);
        return ResponseEntity.ok().build();
    }

	/*public ResponseEntity<List<TicketDTO>> servicesAbertos() {
		List<TicketDTO> lisTickets = new ArrayList<>();

		for (Ticket ticket : ticketRepository.servicesAbertos()) {
			System.out.println(ticket);
			lisTickets.add(new TicketDTO(ticket));
		}

		return new ResponseEntity<>(lisTickets, HttpStatus.OK);
	}*/

	/*public ResponseEntity<List<serviceDTO>> servicesFechados() {
		List<serviceDTO> lisTickets = new ArrayList<>();

		for (service service : serviceRepository.findAll()) {
			lisTickets.add(new serviceDTO(service));
		}

		return new ResponseEntity<>(lisTickets, HttpStatus.OK);
	}*/

    public ResponseEntity<?> delete(Long id) {
        try {
            serviceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException a) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> update(Long id, Service service) {
        try {
            Service serviceAtualizado = serviceRepository.findById(id).map(info -> {
                if (service.getIdTechnical() != null) {
                    info.setIdTechnical(service.getIdTechnical());
                }

                if (service.getStatus() != null) {
                    info.setStatus(service.getStatus());
                } else if (service.getTechnicalAcceptService() != null) {
                    info.setTechnicalAcceptService(service.getTechnicalAcceptService());
                }

                if (service.getUpdateServiceDate() != null) {
                    info.setUpdateServiceDate(service.getUpdateServiceDate());
                } else if (service.getFinalizedServiceDate() != null) {
                    info.setFinalizedServiceDate(service.getFinalizedServiceDate());
                }
                return serviceRepository.save(info);
            }).orElse(null);

            if (serviceAtualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
