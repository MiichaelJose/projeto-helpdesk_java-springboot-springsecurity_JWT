package com.helpdesk.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    @Column(unique = true)
    @CPF(message = "CPF INVALIDO")
    private String cpf;
    private String name;
    @Column(nullable = true, columnDefinition = "TEXT", length = 10000)
    private String image;
    private String role;
    @Column(nullable = false)
    private Boolean permission;
}
