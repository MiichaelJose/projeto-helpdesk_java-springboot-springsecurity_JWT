package com.helpdesk.controllers.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.helpdesk.models.dtos.ServicoDTO;
import com.helpdesk.models.dtos.TicketDTO;
import com.helpdesk.models.entity.Servico;
import com.helpdesk.models.entity.Ticket;
import com.helpdesk.models.entity.Usuario;
import com.helpdesk.models.repositorys.ServicoRepository;
import com.helpdesk.models.repositorys.TicketRepository;

import lombok.extern.slf4j.Slf4j;

@Component
public class ServicoProcess {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	// cadastrar servico
	public ResponseEntity<?> cadastrarServico(Servico servico) {

		servicoRepository.save(servico);


		return ResponseEntity.ok().build();
	}

	// listar servico aberto
	public ResponseEntity<List<TicketDTO>> servicosAbertos() {
		List<TicketDTO> lisTickets = new ArrayList<>();

		System.out.println("aqui");

		for (Ticket ticket : ticketRepository.servicosAbertos()) {
			System.out.println(ticket);
			lisTickets.add(new TicketDTO(ticket));
		}

		return new ResponseEntity<>(lisTickets, HttpStatus.OK);
	}

	// listar servico fechado
	public ResponseEntity<List<ServicoDTO>> servicosFechados() {
		List<ServicoDTO> lisTickets = new ArrayList<>();
		
		for (Servico servico : servicoRepository.findAll()) {
			lisTickets.add(new ServicoDTO(servico));
		}

		return new ResponseEntity<>(lisTickets, HttpStatus.OK);
	}

	// deletar servico
	public ResponseEntity<?> deletarServico(Long id) {
		try {
			servicoRepository.deleteById(id);


			return ResponseEntity.ok().build();
		} catch (EmptyResultDataAccessException a) {

			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<?> alterarServico(Long id, Servico servico) {
		try {
			System.out.println(servico.getStatusServico());
			Servico servicoAtualizado = servicoRepository.findById(id).map(info -> {
				if(servico.getIdTecnico() != null) {
					info.setIdTecnico(servico.getIdTecnico());
				}
				
				if(servico.getStatusServico() != null) {
					info.setStatusServico(servico.getStatusServico());
				}else if(servico.getTecnicoAceitarServico() != null) {
					info.setTecnicoAceitarServico(servico.getTecnicoAceitarServico());
				}
					
				if(servico.getDataServicoInicializado() != null) {
					info.setDataServicoInicializado(servico.getDataServicoInicializado());
				}else if(servico.getDataServicoFinalizado() != null) {
					info.setDataServicoFinalizado(servico.getDataServicoFinalizado());
				}
				
				
				//info.setSenha(passwordEncoder.encode(usuario.getSenha()));

				return servicoRepository.save(info);
			}).orElse(null);

			if (servicoAtualizado == null) {

				return ResponseEntity.notFound().build();
			}


			return ResponseEntity.ok().build();
		} catch (Exception e) {

			return ResponseEntity.badRequest().build();
		}

	}
}
