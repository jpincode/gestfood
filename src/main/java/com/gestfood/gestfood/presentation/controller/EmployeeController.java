package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dtos.EmployeeDTO;
import com.gestfood.gestfood.business.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDto) {
        boolean result = employeeService.save(employeeDto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating employee.");
        }

        return ResponseEntity.ok().body("Employee created successfully!");
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDto) {
        boolean result = employeeService.update(employeeDto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee.");
        }

        return ResponseEntity.ok().body("Employee updated successfully!");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@RequestBody EmployeeDTO employeeDto) {
        boolean result = employeeService.delete(employeeDto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting employee.");
        }

        return ResponseEntity.ok().body("Employee deleted successfully!");
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.findAll();
        if (employees == null || employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No employees found.");
        }

        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.findById(id);
        if (employeeDTO != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + id);
        }

        return ResponseEntity.ok(employeeDTO);
    }
}
