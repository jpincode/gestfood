package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.EmployeeDTO;
import com.gestfood.gestfood.model.entity.Employee;
import com.gestfood.gestfood.model.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService implements InnerDefaultCRUD<EmployeeDTO> {
    @Autowired
    private ConverterService converterService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void create(EmployeeDTO employeeDTO) {
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

    @Override
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

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new RuntimeException("Employee ID cannot be null or less than or equal to zero");
            }
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<EmployeeDTO> read() {
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
    
    @Override
    public EmployeeDTO read(Long id) {
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
