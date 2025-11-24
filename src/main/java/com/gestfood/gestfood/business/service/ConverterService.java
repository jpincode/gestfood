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
        return new DeskDTO(desk.getId(), desk.getSeats());
    }

    public Desk dtoToDesk(DeskDTO deskDTO) {
        return new Desk(deskDTO.getId(), deskDTO.getSeats());
    }

    public Product dtoToProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getDescription());
    }

    public ProductDTO productToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }
    
    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getEmail(), employeeDTO.getPassword(), employeeDTO.getCpf(), employeeDTO.getRole());
    }

    public EmployeeDTO employeeToDto(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getEmail(), employee.getPassword(), employee.getCpf(), employee.getRole());
    }
}
