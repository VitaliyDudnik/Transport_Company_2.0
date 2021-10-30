package com.example.transport_company.service;

import com.example.transport_company.entity.Department;
import com.example.transport_company.entity.Role;
import com.example.transport_company.entity.Status;
import com.example.transport_company.entity.User;
import com.example.transport_company.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TokenService tokenService;

    public void saveRole(Role role) {
        roleService.save(role);
    }

    public void saveDepart(Department department) {
        departmentService.save(department);
    }

    public void saveStatus(Status status) {
        statusService.save(status);
    }

    public User findById(long id) {
        return userService.findById(id);
    }

    public List<User> findUsers() {
        return userService.findAll();
    }

    public List<Department> findDepartments() {
        return departmentService.findAll();
    }

    public List<Role> findRoles() {
        return roleService.findAll();
    }

    public List<Status> findStatuses() {
        return statusService.findAll();
    }

    public void updateUserStatus(long id, long statusId) {
        User user = userService.findById(id);
        Status status = statusService.findById(statusId);
        adminRepository.updateUserStatus(user.getId(), status.getId());
        if (status.getName().equals("logout")) {
            tokenService.deleteByUserId(id);
        }
    }

    public void updateUserRole(long userId, long roleId) {
        userService.findById(userId);
        roleService.getRoleById(roleId);
        adminRepository.updateUserRole(userId, roleId);
    }

    public void deleteUserById(long id) {
        userService.deleteByUserId(id);
    }

    public void deleteDepartment(long id) {
        departmentService.delete(id);
    }

    public void deleteRole(long id) {
        roleService.delete(id);
    }

    public void deleteStatus(long id) {
        statusService.delete(id);
    }
}
