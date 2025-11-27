package com.gestfood.gestfood.business.service;

import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dto.ClientDTO;
import com.gestfood.gestfood.business.dto.CreateDeskDTO;
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
    public CreateDeskDTO deskToDto(Desk desk) {
        CreateDeskDTO dto = new CreateDeskDTO();
        if(desk.getId() != null) {
            dto.setId(desk.getId());
        }
        if(desk.getSeats() > 0) {
            dto.setSeats(desk.getSeats());
        }
        return dto;
    }

    public Desk dtoToDesk(CreateDeskDTO deskDTO) {
        Desk desk = new Desk();
        if(deskDTO.getId() != null) {
            desk.setId(deskDTO.getId());
        }
        if(deskDTO.getSeats() > 0) {
            desk.setSeats(deskDTO.getSeats());
        }
        return desk;
    }

    public Product dtoToProduct(ProductDTO productDTO) {
        Product product = new Product();
        if(productDTO.getId() != null) {
            product.setId(productDTO.getId());
        }
        if(productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if(productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if(productDTO.getDescription() != null) {
            product.setDescription(productDTO.getDescription());
        }
        return product;
    }

    public ProductDTO productToDto(Product product) {
        ProductDTO dto = new ProductDTO();
        if(product.getId() != null) {
            dto.setId(product.getId());
        }
        if(product.getName() != null) {
            dto.setName(product.getName());
        }
        if(product.getPrice() != null) {
            dto.setPrice(product.getPrice());
        }
        if(product.getDescription() != null) {
            dto.setDescription(product.getDescription());
        }
        return dto;
    }
    
    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        if(employeeDTO.getId() != null) {
            employee.setId(employeeDTO.getId());
        }
        if(employeeDTO.getName() != null) {
            employee.setName(employeeDTO.getName());
        }
        if(employeeDTO.getEmail() != null) {
            employee.setEmail(employeeDTO.getEmail());
        }
        if(employeeDTO.getPassword() != null) {
            employee.setPassword(employeeDTO.getPassword());
        }
        if(employeeDTO.getCpf() != null) {
            employee.setCpf(employeeDTO.getCpf());
        }
        if(employeeDTO.getRole() != null) {
            employee.setRole(employeeDTO.getRole());
        }
        return employee;
    }

    public EmployeeDTO employeeToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        if(employee.getId() != null) {
            dto.setId(employee.getId());
        }
        if(employee.getName() != null) {
            dto.setName(employee.getName());
        }
        if(employee.getEmail() != null) {
            dto.setEmail(employee.getEmail());
        }
        if(employee.getPassword() != null) {
            dto.setPassword(employee.getPassword());
        }
        if(employee.getCpf() != null) {
            dto.setCpf(employee.getCpf());
        }
        if(employee.getRole() != null) {
            dto.setRole(employee.getRole());
        }
        return dto;
    }

    public OrderDTO orderToDto(Order order) {
        OrderDTO dto = new OrderDTO();
        if(order.getId() != null) {
            dto.setId(order.getId());
        }
        if(order.getDescription() != null) {
            dto.setDescription(order.getDescription());
        }
        if(order.getTotalAmount() != null) {
            dto.setTotalAmount(order.getTotalAmount());
        }
        if(order.getStatus() != null) {
            dto.setStatus(order.getStatus());
        }
        if(order.getClient() != null) {
            dto.setClient(this.clientToDto(order.getClient()));
        }
        if(order.getProducts() != null) {
            dto.setProducts(order.getProducts().stream().map(this::productToDto).toList());
        }
        return dto;
    }

    public Order dtoToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        if(orderDTO.getId() != null) {
            order.setId(orderDTO.getId());
        }
        if(orderDTO.getDescription() != null) {
            order.setDescription(orderDTO.getDescription());
        }
        if(orderDTO.getTotalAmount() != null) {
            order.setTotalAmount(orderDTO.getTotalAmount());
        }
        if(orderDTO.getStatus() != null) {
            order.setStatus(orderDTO.getStatus());
        }
        if(orderDTO.getClient() != null) {
            order.setClient(this.dtoToClient(orderDTO.getClient()));
        }
        if(orderDTO.getProducts() != null) {
            order.setProducts(orderDTO.getProducts().stream().map(this::dtoToProduct).toList());
        }
        return order;
    }

    private Client dtoToClient(ClientDTO clientDTO) {
        Client client = new Client();
        if(clientDTO.getId() != null) {
            client.setId(clientDTO.getId());
        }
        if(clientDTO.getName() != null) {
            client.setName(clientDTO.getName());
        }
        if(clientDTO.getEmail() != null) {
            client.setEmail(clientDTO.getEmail());
        }
        if(clientDTO.getPassword() != null) {
            client.setPassword(clientDTO.getPassword());
        }
        if(clientDTO.getCpf() != null) {
            client.setCpf(clientDTO.getCpf());
        }
        if(clientDTO.getPhoneNumber() != null) {
            client.setPhoneNumber(clientDTO.getPhoneNumber());
        }
        if(clientDTO.getAddress() != null) {
            client.setAddress(clientDTO.getAddress());
        }
        return client;
    }

    private ClientDTO clientToDto(Client client) {
        ClientDTO dto = new ClientDTO();
        if(client.getId() != null) {
            dto.setId(client.getId());
        }
        if(client.getName() != null) {
            dto.setName(client.getName());
        }
        if(client.getEmail() != null) {
            dto.setEmail(client.getEmail());
        }
        if(client.getPassword() != null) {
            dto.setPassword(client.getPassword());
        }
        if(client.getCpf() != null) {
            dto.setCpf(client.getCpf());
        }
        if(client.getPhoneNumber() != null) {
            dto.setPhoneNumber(client.getPhoneNumber());
        }
        if(client.getAddress() != null) {
            dto.setAddress(client.getAddress());
        }
        return dto;
    }
}
