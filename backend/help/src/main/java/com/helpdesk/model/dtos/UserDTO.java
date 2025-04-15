package com.helpdesk.model.dtos;

import com.helpdesk.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String cpf;

    public UserDTO(User u) {
        id = u.getId();
        name = u.getName();
        cpf = u.getCpf();
    }
}
