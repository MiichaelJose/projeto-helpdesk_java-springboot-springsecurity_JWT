package com.helpdesk.model.dtos;

import com.helpdesk.model.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private UserDTO User;
    private String priority;
    private String department;
    private String color;
    private String date;
    private String description;
    private String serial;
    private String equipment;
    private String image;

    public TicketDTO(Ticket t) {
        id = t.getId();
        priority = t.getPriority();
        department = t.getDepartment();
        color = t.getColor();
        date = t.getDate();
        description = t.getDescription();
        serial = t.getSerial();
        equipment = t.getEquipment();
        image = t.getImage();
    }
}