package com.helpdesk.model.dtos;

import com.helpdesk.model.entity.Service;
import lombok.Data;

@Data
public class ServiceDTO {
    private UserDTO technical;
    private TicketDTO ticket;
    private String statusService;
    private Boolean technicalAcceptService;
    private String updateServiceDate;
    private String finalizedServiceDate;

    public ServiceDTO(Service s) {
        technical = new UserDTO(s.getIdAdmin());
        ticket = new TicketDTO(s.getIdTicket());
        statusService = s.getStatus();
        technicalAcceptService = s.getTechnicalAcceptService();
        updateServiceDate = s.getUpdateServiceDate();
        finalizedServiceDate = s.getFinalizedServiceDate();
    }
}
