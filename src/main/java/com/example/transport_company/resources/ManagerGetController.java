package com.example.transport_company.resources;

import com.example.transport_company.entity.Customer;
import com.example.transport_company.entity.Employee;
import com.example.transport_company.entity.Order;
import com.example.transport_company.entity.Vehicle;
import com.example.transport_company.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerGetController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/vehicle")
    public Vehicle getVehicleById(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return managerService.getVehicleById(id);
    }

    @GetMapping("/vehicle/all")
    public List<Vehicle> getVehicles(@RequestHeader(name = "X-Token") UUID xToken) {
        return managerService.getVehicles();
    }

    @GetMapping("/vehicle/status")
    public List<Vehicle> getVehiclesByStatusId(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return managerService.getVehicleByStatusId(id);
    }

    @GetMapping("/order/status")
    public List<Order> getOrdersByStatusId(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return managerService.getOrdersByStatusId(id);
    }

    @GetMapping("/order")
    public Order getOrderById(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return managerService.getOrderById(id);
    }

    @GetMapping("/order/all")
    public List<Order> getOrders(@RequestHeader(name = "X-Token") UUID xToken) {
        return managerService.findOrders();
    }

    @GetMapping("/customer")
    public Customer getCustomer(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return managerService.getCustomerById(id);
    }

    @GetMapping("/customer/all")
    public List<Customer> getCustomers(@RequestHeader(name = "X-Token") UUID xToken) {
        return managerService.findCustomers();
    }

    @GetMapping("/employee")
    public Employee getEmployee(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return managerService.getEmployeeById(id);
    }

    @GetMapping("/employee/all")
    public List<Employee> getEmployees(@RequestHeader(name = "X-Token") UUID xToken) {
        return managerService.findEmployees();
    }
}
