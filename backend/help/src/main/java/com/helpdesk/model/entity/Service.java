package com.helpdesk.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Boolean technicalAcceptService;
    private String updateServiceDate;
    private String finalizedServiceDate;
    @ManyToOne
    @JoinColumn(name = "id_user_technical")
    private User idTechnical;
    @ManyToOne
    @JoinColumn(name = "id_user_admin")
    private User idAdmin;
    @ManyToOne
    @JoinColumn(name = "id_ticket")
    private Ticket idTicket;
}
