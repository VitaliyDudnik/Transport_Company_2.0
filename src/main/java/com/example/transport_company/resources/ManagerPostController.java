package com.example.transport_company.resources;

import com.example.transport_company.entity.Customer;
import com.example.transport_company.entity.Employee;
import com.example.transport_company.entity.Vehicle;
import com.example.transport_company.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerPostController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/customer/add")
    public void saveCustomer(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Customer customer) {
        managerService.saveCustomer(customer);
    }

    @PostMapping("/employee/add")
    public void saveEmployee(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Employee employee) {
        managerService.saveEmployee(employee);
    }

    @PostMapping("/customer/addOrder")
    public void addOrderCustomer(@RequestHeader(name = "X-Token") UUID xToken, String orderEmail, long customerId) {
        managerService.addOrder(orderEmail, customerId);
    }

    @PostMapping("/vehicle/add")
    public void saveVehicle(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Vehicle vehicle) {
        managerService.saveVehicle(vehicle);
    }
}
