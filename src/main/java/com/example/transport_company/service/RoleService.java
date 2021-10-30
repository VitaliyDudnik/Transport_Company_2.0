package com.example.transport_company.service;

import com.example.transport_company.RestPreconditions;
import com.example.transport_company.entity.Role;
import com.example.transport_company.exception.ResourceConflictException;
import com.example.transport_company.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void save(Role role) {
        boolean exists = RestPreconditions.checkResource(roleRepository.existsByName(role.getName()));
        if (!exists) {
            roleRepository.save(role);
        } else {
            throw new ResourceConflictException("Role already exists");
        }
    }

    public Role getRoleById(long id) {
        return RestPreconditions.checkResource(roleRepository.findRoleById(id));
    }

    public Role getRoleByName(String name) {
        return RestPreconditions.checkResource(roleRepository.findRoleByName(name));
    }

    public List<Role> findAll() {
        return RestPreconditions.checkResource(roleRepository.findAll());
    }

    public void delete(long id) {
        getRoleById(id);
        roleRepository.deleteById(id);
    }
}
