package com.helpdesk.repository;

import com.helpdesk.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from ticket as t where not exists (select *  from Service as s where t.id = s.id_ticket)"
    )
    List<Ticket> ServicesAbertos();
}
