package com.example.transport_company.service;

import com.example.transport_company.entity.User;
import com.example.transport_company.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    public void deleteToken(UUID xToken) {
        tokenService.delete(xToken);
    }

    public UUID makeLogin(String email, String password) {
        RestPreconditions.checkResources(email, password);
        return userService.doLogin(email, password);
    }

    public void saveUser(User user) {
        userService.save(user);
    }
}
