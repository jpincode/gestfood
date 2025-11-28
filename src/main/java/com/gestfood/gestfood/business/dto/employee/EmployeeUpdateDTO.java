package com.gestfood.gestfood.business.dto.employee;

import com.gestfood.gestfood.model.entity.Employee;

public record EmployeeUpdateDTO(Long id, String name, String email, String cpf, String password, String role) {
    public EmployeeUpdateDTO(Employee e) {
        this(e.getId(), e.getName(), e.getEmail(), e.getCpf(), e.getPassword(), e.getRole());
    }
}
