package com.helpdesk.models.repositorys;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helpdesk.models.entity.Servico;
import com.helpdesk.models.entity.Ticket;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	
}
