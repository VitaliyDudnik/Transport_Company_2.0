package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Employee;
import com.example.transport_company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentService departmentService;

    @CachePut(value = "employees")
    public void save(Employee employee) {
        RestPreconditions.checkResource(employee);
        employeeRepository.save(employee);
    }

    @Cacheable(value = "employees", key = "#id")
    public Employee getById(long empId) {
        return RestPreconditions.checkResource(employeeRepository.findEmployeeById(empId));
    }

    @Cacheable(value = "employees")
    public List<Employee> findAll() {
        return RestPreconditions.checkResource(employeeRepository.findAll());
    }

    public void updateDepartment(long employeeId, long departmentId) {
        getById(employeeId);
        departmentService.getById(departmentId);
        employeeRepository.updateDepartment(employeeId, departmentId);
    }

    @CacheEvict(value = "employees", key = "#id")
    public void delete(long id) {
        getById(id);
        employeeRepository.deleteById(id);
    }
}
