package com.helpdesk.models.repositorys;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.helpdesk.models.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	@Query(
			nativeQuery = true,
			value = "select * from ticket as t where not exists (select *  from servico as s where t.id = s.id_ticket)"
	)
	List<Ticket> servicosAbertos();
}
