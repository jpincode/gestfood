package com.gestfood.gestfood.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long cpf;
}
