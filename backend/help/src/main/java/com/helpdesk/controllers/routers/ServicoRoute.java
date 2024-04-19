package com.helpdesk.controllers.routers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.helpdesk.controllers.process.ServicoProcess;
import com.helpdesk.models.dtos.ServicoDTO;
import com.helpdesk.models.dtos.TicketDTO;
import com.helpdesk.models.entity.Servico;

@RestController
@RequestMapping("servico")
public class ServicoRoute {

	@Autowired
	private ServicoProcess process;
	
	@GetMapping("/abertos")
	public ResponseEntity<List<TicketDTO>> servicosAbertos() {
		return process.servicosAbertos();
	}
	
	@GetMapping("/fechados")
	public ResponseEntity<List<ServicoDTO>> servicosFechados() {
		return process.servicosFechados();
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrarServico(@RequestBody Servico servico) {
		return process.cadastrarServico(servico);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletarServico(@PathVariable Long id) {
		return process.deletarServico(id);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> alterarServico(@PathVariable Long id, @RequestBody Servico servico) {
		return process.alterarServico(id, servico);
	}
	
}
