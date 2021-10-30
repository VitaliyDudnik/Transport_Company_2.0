package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ManagerService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private StatusService statusService;

    public void saveCustomer(Customer customer) {
        RestPreconditions.checkResource(customer);
        Set<Order> order = orderService.getAllByEmail(customer.getEmail());
        customer.setOrders(order);
        customerService.save(customer);
    }

    public void saveEmployee(Employee employee) {
        employeeService.save(employee);
    }

    public void saveVehicle(Vehicle vehicle) {
        vehicleService.save(vehicle);
    }

    public List<Order> getOrdersByStatusId(long id) {
        return RestPreconditions.checkResource(orderService.findAllByStatusId(id));
    }

    public Order getOrderById(long id) {
        return orderService.findById(id);
    }

    public List<Order> findOrders() {
        return orderService.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerService.getById(id);
    }

    public List<Customer> findCustomers() {
        return customerService.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeService.getById(id);
    }

    public List<Employee> findEmployees() {
        return employeeService.findAll();
    }

    public Vehicle getVehicleById(long id) {
        return vehicleService.findById(id);
    }

    public List<Vehicle> getVehicles() {
        return vehicleService.findAll();
    }

    public List<Vehicle> getVehicleByStatusId(long id) {
        return vehicleService.findByStatusId(id);
    }

    public void updateVehicleStatus(long id, long statusId) {
        Status status = statusService.findById(statusId);
        vehicleService.updateStatus(id, status.getName());
    }

    public void updateOrderStatus(long orderId, long id) {
        orderService.updateOrderStatus(orderId, id);
    }

    public void addOrder(String orderEmail, long customerId) {
        customerService.addOrder(orderEmail, customerId);
    }

    public void updateEmpDepartment(long empId, long departId) {
        employeeService.updateDepartment(empId, departId);
    }

    public void updateEmployee(Employee employee) {
        employeeService.save(employee);
    }

    public void updateCustomer(Customer customer) {
        customerService.save(customer);
    }

    public void deleteCustomer(long customerId) {
        customerService.delete(customerId);
    }

    public void deleteEmployee(long employeeId) {
        employeeService.delete(employeeId);
    }

    public void deleteOrder(long orderId) {
        orderService.deleteById(orderId);
    }

    public void deleteVehicle(long id) {
        vehicleService.delete(id);
    }
}
