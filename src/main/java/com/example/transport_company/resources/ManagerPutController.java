package com.example.transport_company.resources;

import com.example.transport_company.entity.Customer;
import com.example.transport_company.entity.Employee;
import com.example.transport_company.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
public class ManagerPutController {

    @Autowired
    private ManagerService managerService;

    @PutMapping("/order/status")
    public void updateOrderStatus(@RequestHeader(name = "X-Token") UUID xToken, long orderId, long statusId) {
        managerService.updateOrderStatus(orderId, statusId);
    }

    @PutMapping("/employee/department")
    public void updateEmployeeDepartment(@RequestHeader(name = "X-Token") UUID xToken, long employeeId, long departmentId) {
        managerService.updateEmpDepartment(employeeId, departmentId);
    }

    @PutMapping("/employee")
    public void updateEmployee(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Employee employee) {
        managerService.updateEmployee(employee);
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Customer customer) {
        managerService.updateCustomer(customer);
    }

    @PutMapping("/vehicle/status")
    public void updateVehicleStatus(@RequestHeader(name = "X-Token") UUID xToken, long id, long statusId) {
        managerService.updateVehicleStatus(id, statusId);
    }
}
