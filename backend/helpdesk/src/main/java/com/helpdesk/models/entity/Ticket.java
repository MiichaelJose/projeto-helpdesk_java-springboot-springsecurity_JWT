package com.helpdesk.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param relação unidirecional, apenas Ticket esta ciente do relacionamento
	 * entre o Usuario
	 */
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario idUsuario;

	//private Long idFuncionario;
	
	// o usuario vai definir ao nivel de importancia em que deve ser solucionado o seu problema
	private String statusPrioridade;

	// departamento em que se encontra o equipamento
	private String departamento;

	// cor do equipamento						
	private String cor;

	// data em que foi gerado o ticket
	private String data;

	// sobre o problema em que se encontra o equipamento
	private String descricao;

	// numero de fabrica do equipamento ( não obrigatório )
	private String serial;

	// nome do produto
	private String nomeEquipamento;

	// foto do produto ão obrigatório
	private String imagem;
}
