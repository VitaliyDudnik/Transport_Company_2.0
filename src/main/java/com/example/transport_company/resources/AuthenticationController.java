package com.example.transport_company.resources;

import com.example.transport_company.entity.User;
import com.example.transport_company.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        authService.saveUser(user);
        return new ResponseEntity<>("User successfully saved", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(String email, String password) {
        UUID token = authService.makeLogin(email, password);
        return new ResponseEntity<>("Your personal key to confirm transactions >> " + token, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader(name = "X-Token") UUID xToken) {
        authService.deleteToken(xToken);
        return new ResponseEntity<>("You have been successfully logged out.", HttpStatus.OK);
    }
}
