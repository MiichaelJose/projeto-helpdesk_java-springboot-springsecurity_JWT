package com.helpdesk.models.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.models.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	/*
	@Query(value = "SELECT * FROM usuario u WHERE u.usuario = :usuario", nativeQuery = true)
	Usuario findUserByUsuario(@Param("usuario") String usuario);
	*/
	
	Usuario findByUsuario(String usuario);
}
