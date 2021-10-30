package com.example.transport_company.repository;

import com.example.transport_company.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentById(long id);
}
