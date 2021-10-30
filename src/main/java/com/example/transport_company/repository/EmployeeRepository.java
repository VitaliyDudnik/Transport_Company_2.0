package com.example.transport_company.repository;

import com.example.transport_company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("update Employee e set e.department.id = :departId where e.id = :empId")
    void updateDepartment(@Param("empId") long empId, @Param("departId") long departId);

    @Transactional(readOnly = true)
    Employee findEmployeeById(long id);

}
