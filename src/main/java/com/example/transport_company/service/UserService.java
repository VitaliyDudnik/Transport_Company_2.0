package com.example.transport_company.service;

import com.example.transport_company.entity.*;
import com.example.transport_company.exception.ResourceConflictException;
import com.example.transport_company.jasypt.JasyptService;
import com.example.transport_company.repository.*;
import com.example.transport_company.RestPreconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class UserService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JasyptService encryptor;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StatusService statusService;

    @CachePut(cacheNames = {"user", "allUsers"})
    public void save(User user) {
        log.info("Performing save user");
        RestPreconditions.checkResource(user);
        user.setRoles(roleService.getRoleById(5));
        user.setStatus(statusService.findById(4));
        user.setPassword(encryptor.encrypt(user.getPassword()));
        userRepository.save(user);
    }

    public void saveOrder(Order order) {
        orderService.save(order);
    }

    public Order getOrder(long id) {
        return orderService.findById(id);
    }

    @CacheEvict({"allUsers", "user"})
    public void updateEmail(String password, String newEmail, UUID xToken) {
        RestPreconditions.checkResources(newEmail, password);

        Token token = tokenService.findTokenByUUID(xToken);
        List<User> userList = userRepository.findAll();

        boolean emailMatches = userList.stream().anyMatch(user -> (user.getEmail().equals(newEmail)));
        boolean passwordMatches = password.equals(encryptor.decrypt(token.getUser().getPassword()));
        if (!emailMatches & passwordMatches) {
            userRepository.updateUserEmail(token.getUser().getId(), newEmail);
        } else {
            throw new ResourceConflictException();
        }
    }

    @CacheEvict({"allUsers", "user"})
    public void updateUserPassword(String oldPassword, String newPassword, UUID xToken) {
        RestPreconditions.checkResources(oldPassword, newPassword);
        Token token = tokenService.findTokenByUUID(xToken);
        boolean matches = oldPassword.equals(encryptor.decrypt(token.getUser().getPassword()));
        if (matches) {
            userRepository.updateUserPassword(token.getUser().getId(), encryptor.encrypt(newPassword));
        } else {
            throw new ResourceConflictException();
        }
    }

    public UUID doLogin(String email, String password) {
        boolean login = checkLogin(email, password);
        Token token = new Token();
        if (login) {
            log.info("Performing xToken generation individual procedure");
            User user = getUserByEmail(email);
            user.setStatus(statusService.findById(3));
            token.setUser(user);
            token.setUuid(UUID.randomUUID());
            tokenService.save(token);
        }
        return token.getUuid();
    }

    public boolean checkLogin(String email, String password) {
        log.info("Performing the login procedure");
        User user = getUserByEmail(email);
        boolean passMatches = password.equals(encryptor.decrypt(user.getPassword()));
        boolean loginTwice = user.getStatus().getId() == 3;
        if (!passMatches) {
            throw new ResourceConflictException();
        }
        if (loginTwice) {
            throw new ResourceConflictException("You are already logged in");
        }
        return true;
    }

    @Cacheable(cacheNames = {"user","allUsers"},  key = "#id")
    public User findById(long id) {
        return RestPreconditions.checkResource(userRepository.getUserById(id));
    }

    @Cacheable(value = "allUsers")
    public List<User> findAll() {
        return RestPreconditions.checkResource(userRepository.findAll());
    }

    @Cacheable(cacheNames = {"allUsers", "user"}, key = "#email")
    public User getUserByEmail(String email) {
        RestPreconditions.checkResource(email);
        return RestPreconditions.checkResource(userRepository.getUserByEmail(email));
    }

    @CacheEvict({"user", "allUsers"})
    public void delete(UUID xToken) {
        log.info("Performing user self-deletion");
        Token token = tokenService.findTokenByUUID(xToken);
        tokenService.deleteByUserId(token.getUser().getId());
        userRepository.deleteById(token.getUser().getId());
    }

    @CacheEvict(value = "user", key = "#id")
    public void deleteByUserId(long id) {
        log.info("Performing delete user by ID: " + id);
        findById(id);
        tokenService.deleteByUserId(id);
        userRepository.deleteById(id);
    }
}

