package com.example.transport_company.resources;

import com.example.transport_company.entity.Department;
import com.example.transport_company.entity.Role;
import com.example.transport_company.entity.Status;
import com.example.transport_company.entity.User;
import com.example.transport_company.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/role/add")
    public void addRole(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Role role) {
        adminService.saveRole(role);
    }

    @PostMapping("/department/add")
    public void addDepart(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Department department) {
        adminService.saveDepart(department);
    }

    @PostMapping("/status/add")
    public void addStatus(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Status status) {
        adminService.saveStatus(status);
    }

    @GetMapping("/user")
    public User getUserById(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return adminService.findById(id);
    }

    @GetMapping("/user/all")
    public List<User> getUsers(@RequestHeader(name = "X-Token") UUID xToken) {
        return adminService.findUsers();
    }

    @GetMapping("/department/all")
    public List<Department> getDepartments(@RequestHeader(name = "X-Token") UUID xToken) {
        return adminService.findDepartments();
    }

    @GetMapping("/role/all")
    public List<Role> getRoles(@RequestHeader(name = "X-Token") UUID xToken) {
        return adminService.findRoles();
    }

    @GetMapping("/status/all")
    public List<Status> getStatuses(@RequestHeader(name = "X-Token") UUID xToken) {
        return adminService.findStatuses();
    }

    @PutMapping("/user/status")
    public void updateStatus(@RequestHeader(name = "X-Token") UUID xToken, long userId, long statusId) {
        adminService.updateUserStatus(userId, statusId);
    }

    @PutMapping("/user/role")
    public void updateRole(@RequestHeader(name = "X-Token") UUID xToken, long userId, long roleId) {
        adminService.updateUserRole(userId, roleId);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        adminService.deleteUserById(id);
    }

    @DeleteMapping("/department")
    public void deleteDepartment(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        adminService.deleteDepartment(id);
    }

    @DeleteMapping("/role")
    public void deleteRole(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        adminService.deleteRole(id);
    }

    @DeleteMapping("/status")
    public void deleteStatus(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        adminService.deleteStatus(id);
    }
}
