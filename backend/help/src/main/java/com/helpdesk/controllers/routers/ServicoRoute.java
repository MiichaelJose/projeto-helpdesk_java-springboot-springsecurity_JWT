package com.helpdesk.controllers.routers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.controllers.process.ServicoProcess;
import com.helpdesk.models.dtos.TicketDTO;

@RestController
@RequestMapping("servico")
public class ServicoRoute {

	@Autowired
	private ServicoProcess process;
	
	@GetMapping
	public ResponseEntity<List<TicketDTO>> servicoAberto() {
		return process.servicosAbertos();
	}
	
}
