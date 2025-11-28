package com.gestfood.gestfood.model.entity;

import com.gestfood.gestfood.business.dto.employee.EmployeeRequestDTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AbstractUser {
    private String role;

    public Employee(EmployeeRequestDTO dto) {
        this.setName(dto.name());
        this.setEmail(dto.email());
        this.setCpf(dto.cpf());
        this.setPassword(dto.password());
        this.role = dto.role();
    }
}
