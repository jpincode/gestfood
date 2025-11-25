package com.gestfood.gestfood.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.EmployeeDTO;
import com.gestfood.gestfood.business.exception.InternalServerErrorException;
import com.gestfood.gestfood.business.exception.NoContentException;
import com.gestfood.gestfood.business.exception.ResourceConflictException;
import com.gestfood.gestfood.business.exception.ResourceNotFoundException;
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
                throw new ResourceConflictException("CPF", employee.getCpf());
            }

            if(employee.getEmail().equals(employeeExists.get().getEmail())) {
                throw new ResourceConflictException("Email", employee.getEmail());
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
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
            if (existingEmployee.isEmpty()) {
                throw new ResourceNotFoundException("Funcionário", employee.getId());
            }
            employee.setId(existingEmployee.get().getId());
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
                throw new InternalServerErrorException("O ID do funcionário não pode ser nulo nem menor ou igual a zero.");
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
                throw new NoContentException("funcionário");
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
                throw new ResourceNotFoundException("Funcionário", id);
            }
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isEmpty()) {
                throw new ResourceNotFoundException("Funcionário", id);
            }
            return converterService.employeeToDto(employee.get());
        } catch (Exception e) {
            throw e;
        }
    }
}
