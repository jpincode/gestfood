package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dto.employee.EmployeeRequestDTO;
import com.gestfood.gestfood.business.dto.employee.EmployeeResponseDTO;
import com.gestfood.gestfood.business.dto.employee.EmployeeUpdateDTO;
import com.gestfood.gestfood.business.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequestDTO dto) {
        employeeService.create(dto);
        return ResponseEntity.ok().body("Funcionário cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateDTO dto) {
        employeeService.update(id, dto);
        return ResponseEntity.ok().body("Funcionário atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok().body("Funcionário deletado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.read(id));
    }
}
