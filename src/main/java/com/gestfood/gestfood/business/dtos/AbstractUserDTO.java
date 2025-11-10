package com.gestfood.gestfood.business.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractUserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long cpf;

    public AbstractUserDTO(Long id, String name, String email, String password, Long cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }
}
