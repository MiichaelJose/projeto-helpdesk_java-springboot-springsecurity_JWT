package com.helpdesk.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user_fk", nullable = false)
    private User idUser;
    private String priority;
    private String department;
    private String color;
    private String date;
    private String description;
    private String serial;
    private String equipment;
    @Column(nullable = true, columnDefinition = "TEXT", length = 10000)
    private String image;
}
