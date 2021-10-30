package com.example.transport_company.resources;

import com.example.transport_company.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerDeleteController {

    @Autowired
    private ManagerService managerService;

    @DeleteMapping("/customer")
    public void deleteCustomer(@RequestHeader(name = "X-Token") UUID xToken, long customerId) {
        managerService.deleteCustomer(customerId);
    }

    @DeleteMapping("/employee")
    public void deleteEmployee(@RequestHeader(name = "X-Token") UUID xToken, long employeeId) {
        managerService.deleteEmployee(employeeId);

    }

    @DeleteMapping("/order")
    public void deleteOrder(@RequestHeader(name = "X-Token") UUID xToken, long orderId) {
        managerService.deleteOrder(orderId);
    }

    @DeleteMapping("/vehicle")
    public void deleteVehicle(@RequestHeader(name = "X-Token") UUID xToken, long vehicleId) {
        managerService.deleteVehicle(vehicleId);
    }
}
