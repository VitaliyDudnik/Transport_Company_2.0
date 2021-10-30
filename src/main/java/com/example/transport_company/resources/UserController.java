package com.example.transport_company.resources;

import com.example.transport_company.entity.Order;
import com.example.transport_company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestHeader(name = "X-Token") UUID xToken, @Valid @RequestBody Order order) {
        userService.saveOrder(order);
        return new ResponseEntity<>("Order has been sent for review.", HttpStatus.OK);
    }

    @PutMapping("/email")
    public ResponseEntity<Object> updateEmail(@RequestHeader(name = "X-Token") UUID xToken, String password, String newEmail) {
        userService.updateEmail(password, newEmail, xToken);
        return new ResponseEntity<>("Email has been successfully updated", HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<Object> updatePassword(@RequestHeader(name = "X-Token") UUID xToken, String oldPassword, String newPassword) {
        userService.updateUserPassword(oldPassword, newPassword, xToken);
        return new ResponseEntity<>("Password has been successfully updated", HttpStatus.OK);
    }

    @GetMapping("/order")
    public Order getOrder(@RequestHeader(name = "X-Token") UUID xToken, long id) {
        return userService.getOrder(id);
    }

    @DeleteMapping()
    public void delete(@RequestHeader(name = "X-Token") UUID xToken) {
        userService.delete(xToken);
    }
}
