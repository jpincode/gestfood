package com.gestfood.gestfood.business.dto.employee;

import com.gestfood.gestfood.model.entity.Employee;

public record EmployeeResponseDTO(Long id, String name, String email, String cpf, String role) {
    public EmployeeResponseDTO(Employee e) {
        this(e.getId(), e.getName(), e.getEmail(), e.getCpf(), e.getRole());
    }
}
