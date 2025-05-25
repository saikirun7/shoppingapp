package com.shopping_app_next.authapi_1.controller;

import com.shopping_app_next.authapi_1.entity.User;
import com.shopping_app_next.authapi_1.exception.UserAlreadyExistsException;
import com.shopping_app_next.authapi_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
            response.put("message", "User logged in successfully");
            response.put("role", user.getRole());
            response.put("customerId", user.getCustomerId());
            response.put("email", user.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        try{
            userService.addUser(user);
            response.put("message", "User registered successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
