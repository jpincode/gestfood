package com.gestfood.gestfood.business.services;

import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dtos.BoardDTO;
import com.gestfood.gestfood.business.dtos.EmployeeDTO;
import com.gestfood.gestfood.business.dtos.ProductDTO;
import com.gestfood.gestfood.models.entities.Board;
import com.gestfood.gestfood.models.entities.Employee;
import com.gestfood.gestfood.models.entities.Product;

@Service
public class ConverterService {
    public BoardDTO boardToDto(Board board) {
        return new BoardDTO(board.getId(), board.getSeats());
    }

    public Board dtoToBoard(BoardDTO boardDTO) {
        return new Board(boardDTO.getId(), boardDTO.getSeats());
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
