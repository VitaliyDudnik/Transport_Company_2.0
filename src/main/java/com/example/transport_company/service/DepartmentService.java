package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Department;
import com.example.transport_company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void save(Department department) {
        RestPreconditions.checkResource(department);
        departmentRepository.save(department);
    }

    public Department getById(long id) {
        return RestPreconditions.checkResource(departmentRepository.findDepartmentById(id));
    }

    public List<Department> findAll() {
        return RestPreconditions.checkResource(departmentRepository.findAll());
    }

    public void delete(long id) {
        RestPreconditions.checkResource(getById(id));
        departmentRepository.deleteById(id);
    }
}
