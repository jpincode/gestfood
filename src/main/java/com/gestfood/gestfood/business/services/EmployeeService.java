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

    public boolean save(EmployeeDTO employeeDTO) {
        try {
            Employee employee = converterService.dtoToEmployee(employeeDTO);
            if (employee == null) {
                throw new RuntimeException("Converted employee is null");
            }
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error saving employee: " + e.getMessage());
        }
    }

    @Transactional
    public boolean update(EmployeeDTO employeeDTO) {
        try {
            Employee employee = converterService.dtoToEmployee(employeeDTO);
            if (employee == null) {
                throw new RuntimeException("Converted employee is null");
            }
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error updating employee: " + e.getMessage());
        }
    }

    @Transactional
    public boolean delete(EmployeeDTO employeeDTO) {
        try {
            Employee employee = converterService.dtoToEmployee(employeeDTO);
            if (employee == null) {
                throw new RuntimeException("Converted employee is null");
            }
            employeeRepository.delete(employee);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee: " + e.getMessage());
        }
    }

    public List<EmployeeDTO> findAll() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeDTO> employeesDto = new ArrayList<>();

            for (Employee employee : employees) {
                employeesDto.add(converterService.employeeToDto(employee));
            }
            
            return employeesDto;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving employees: " + e.getMessage());
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
            throw new RuntimeException("Error retrieving employee by id: " + e.getMessage());
        }
    }
}
