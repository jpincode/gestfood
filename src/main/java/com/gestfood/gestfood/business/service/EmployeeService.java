package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.EmployeeDTO;
import com.gestfood.gestfood.business.exception.EntityConflictException;
import com.gestfood.gestfood.business.exception.EntityNotFoundException;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.model.entity.Employee;
import com.gestfood.gestfood.model.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService implements InnerDefaultCrud<EmployeeDTO> {
    @Autowired
    private ConverterService converterService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void create(EmployeeDTO employeeDTO) {
        Employee employee = converterService.dtoToEmployee(employeeDTO);
        Optional<Employee> employeeExists = employeeRepository.findById(employee.getId());

        if (employee.getCpf().equals(employeeExists.get().getCpf())) {
            throw new EntityConflictException("Já existe um funcionário com o CPF: " + employee.getCpf());
        }

        if(employee.getEmail().equals(employeeExists.get().getEmail())) {
            throw new EntityConflictException("Já existe um funcionário com o email: " + employee.getEmail());
        }
        
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = converterService.dtoToEmployee(employeeDTO);
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isEmpty()) {
            throw new EntityNotFoundException("O funcionário não foi encontrado.");
        }
        employee.setId(existingEmployee.get().getId());
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new InternalServerErrorException("O ID do funcionário não pode ser nulo nem menor ou igual a zero.");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> read() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeesDto = new ArrayList<>();

        if (employees.isEmpty()) {
            throw new EntityNotFoundException("Não existem funcionários cadastrados.");
        }

        for (Employee employee : employees) {
            employeesDto.add(converterService.employeeToDto(employee));
        }
        return employeesDto;
    }
    
    @Override
    public EmployeeDTO read(Long id) {
        if (id == null) {
            throw new InternalServerErrorException("O ID do funcionário não pode ser nulo.");
        }
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EntityNotFoundException("O funcionário com ID: " + id + " não foi encontrado.");
        }
        return converterService.employeeToDto(employee.get());
    }
}
