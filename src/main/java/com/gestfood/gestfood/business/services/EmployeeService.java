package com.gestfood.gestfood.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dtos.EmployeeDTO;
import com.gestfood.gestfood.models.entities.Employee;
import com.gestfood.gestfood.models.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private ConverterService converterService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(EmployeeDTO employeeDTO) {
        try {
            Employee employee = converterService.dtoToEmployee(employeeDTO);
            Optional<Employee> employeeExists = employeeRepository.findById(employee.getId());

            if (employee.getCpf().equals(employeeExists.get().getCpf())) {
                throw new RuntimeException("An employee with this CPF already exists!");
            }

            if(employee.getEmail().equals(employeeExists.get().getEmail())) {
                throw new RuntimeException("This email address is already registered!");
            }
            
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void update(EmployeeDTO employeeDTO) {
        try {
            Employee employee = converterService.dtoToEmployee(employeeDTO);
            if (employee == null) {
                throw new RuntimeException("Converted employee is null");
            }
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void delete(EmployeeDTO employeeDTO) {
        try {
            Employee employee = converterService.dtoToEmployee(employeeDTO);
            if (employee == null) {
                throw new RuntimeException("Converted employee is null");
            }
            employeeRepository.delete(employee);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<EmployeeDTO> findAll() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeDTO> employeesDto = new ArrayList<>();

            if (employees.isEmpty()) {
                throw new RuntimeException("There are no registered employees.");
            }

            for (Employee employee : employees) {
                employeesDto.add(converterService.employeeToDto(employee));
            }
            return employeesDto;
        } catch (Exception e) {
            throw e;
        }
    }

    public EmployeeDTO findById(Long id) {
        try {
            if (id == null) {
                throw new RuntimeException("Employee not found with id: " + id);
            }
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isEmpty()) {
                throw new RuntimeException("Employee not found with id: " + id);
            }
            return converterService.employeeToDto(employee.get());
        } catch (Exception e) {
            throw e;
        }
    }
}
