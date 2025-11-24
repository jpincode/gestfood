package com.gestfood.gestfood.business.service;

import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.DeskDTO;
import com.gestfood.gestfood.business.dto.EmployeeDTO;
import com.gestfood.gestfood.business.dto.ProductDTO;
import com.gestfood.gestfood.model.entity.Desk;
import com.gestfood.gestfood.model.entity.Employee;
import com.gestfood.gestfood.model.entity.Product;

@Service
public class ConverterService {
    public DeskDTO deskToDto(Desk desk) {
        DeskDTO dto = new DeskDTO();
        dto.setId(desk.getId());
        dto.setSeats(desk.getSeats());
        return dto;
    }

    public Desk dtoToDesk(DeskDTO deskDTO) {
        Desk desk = new Desk();
        desk.setId(deskDTO.getId());
        desk.setSeats(deskDTO.getSeats());
        return desk;
    }

    public Product dtoToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        return product;
    }

    public ProductDTO productToDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        return dto;
    }
    
    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setCpf(employeeDTO.getCpf());
        employee.setRole(employeeDTO.getRole());
        return employee;
    }

    public EmployeeDTO employeeToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPassword(employee.getPassword());
        dto.setCpf(employee.getCpf());
        dto.setRole(employee.getRole());
        return dto;
    }
}
