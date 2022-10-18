package com.helpdesk.models.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.models.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
