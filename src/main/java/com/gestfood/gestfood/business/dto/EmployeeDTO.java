package com.gestfood.gestfood.business.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO extends AbstractUserDTO {
    private String role;

    public EmployeeDTO(Long id, String name, String email, String password, Long cpf, String role) {
        super(id, name, email, password, cpf);
        this.role = role;
    }
}
