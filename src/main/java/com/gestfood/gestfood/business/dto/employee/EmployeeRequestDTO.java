package com.gestfood.gestfood.business.dto.employee;

import com.gestfood.gestfood.model.entity.Employee;

public record EmployeeRequestDTO(String name, String email, String cpf, String password, String role) {
    public EmployeeRequestDTO(Employee e) {
        this(e.getName(), e.getEmail(), e.getCpf(), e.getPassword(), e.getRole());
    }
}
