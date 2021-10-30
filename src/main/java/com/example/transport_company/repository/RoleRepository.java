package com.example.transport_company.repository;

import com.example.transport_company.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);

    Role findRoleById(long id);

    boolean existsByName(String name);
}
