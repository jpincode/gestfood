package com.gestfood.gestfood.business.service;

import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.ClientDTO;
import com.gestfood.gestfood.business.dto.DeskDTO;
import com.gestfood.gestfood.business.dto.EmployeeDTO;
import com.gestfood.gestfood.business.dto.OrderDTO;
import com.gestfood.gestfood.business.dto.ProductDTO;
import com.gestfood.gestfood.model.entity.Client;
import com.gestfood.gestfood.model.entity.Desk;
import com.gestfood.gestfood.model.entity.Employee;
import com.gestfood.gestfood.model.entity.Order;
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

    public OrderDTO orderToDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setDescription(order.getDescription());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setClient(this.clientToDto(order.getClient()));
        dto.setProducts(order.getProducts().stream().map(this::productToDto).toList());
        return dto;
    }

    public Order dtoToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setDescription(orderDTO.getDescription());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus(orderDTO.getStatus());
        order.setClient(this.dtoToClient(orderDTO.getClient()));
        order.setProducts(orderDTO.getProducts().stream().map(this::dtoToProduct).toList());
        return order;
    }

    private Client dtoToClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());
        client.setCpf(clientDTO.getCpf());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setAddress(clientDTO.getAddress());
        return client;
    }

    private ClientDTO clientToDto(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPassword(client.getPassword());
        dto.setCpf(client.getCpf());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setAddress(client.getAddress());
        return dto;
    }
}
