package com.gestfood.gestfood.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.employee.EmployeeRequestDTO;
import com.gestfood.gestfood.business.dto.employee.EmployeeResponseDTO;
import com.gestfood.gestfood.business.dto.employee.EmployeeUpdateDTO;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.model.entity.Employee;
import com.gestfood.gestfood.model.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ValidationService validationService;

    public void create(EmployeeRequestDTO dto) {
        validationService.validatePassword(dto.password());
        validationService.validateEmail(dto.email());
        validationService.validateCpf(dto.cpf());

        Employee employee = new Employee(dto);
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(Long id, EmployeeUpdateDTO dto) {
        validationService.validateId(id);
        validationService.validatePassword(dto.password());
        validationService.validateEmail(dto.email());
        validationService.validateCpf(dto.cpf());

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setCpf(dto.cpf());
        employee.setPassword(dto.password());
        employee.setRole(dto.role());
    }

    @Transactional
    public void delete(Long id) {
        validationService.validateId(id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        employeeRepository.delete(employee);
    }

    public List<EmployeeResponseDTO> read() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponseDTO::new)
                .toList();
    }

    public EmployeeResponseDTO read(Long id) {
        validationService.validateId(id);

        return employeeRepository.findById(id)
                .map(EmployeeResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("O funcionário não foi encontrado."));
    }
}
