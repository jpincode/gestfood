package com.gestfood.gestfood.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee extends AbstractUser {
    private String role;

    public Employee() {
        super();
    }

    public Employee(Long id, String name, String email, String password, Long cpf, String role) {
        super(id, name, email, password, cpf);
        this.role = role;
    }

}
