package com.helpdesk.models.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.helpdesk.models.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
